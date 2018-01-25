package com.cys.web.interceptor;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallback;

import java.lang.reflect.Method;

/**
 * Created by liyuan on 2018/1/24.
 */
public class DeTransactionInterceptor extends TransactionInterceptor {

    @Override
    protected Object invokeWithinTransaction(Method method, Class<?> targetClass, final InvocationCallback invocation)
            throws Throwable {

        // If the transaction attribute is null, the method is non-transactional.
        final TransactionAttribute txAttr = getTransactionAttributeSource().getTransactionAttribute(method, targetClass);
        final PlatformTransactionManager tm = determineTransactionManager(txAttr);
        final String joinpointIdentification = methodIdentification(method, targetClass);

        if (txAttr == null || !(tm instanceof CallbackPreferringPlatformTransactionManager)) {
            // Standard transaction demarcation with getTransaction and commit/rollback calls.
            TransactionInfo txInfo = createTransactionIfNecessary(tm, txAttr, joinpointIdentification);
            Object retVal = null;
            try {
                // This is an around advice: Invoke the next interceptor in the chain.
                // This will normally result in a target object being invoked.
                retVal = invocation.proceedWithInvocation();
            }
            catch (Throwable ex) {
                // target invocation exception
                //这里加个判断， 如果是已经存在事物（service调用service），不用回滚被调用的service的事物
                if (txInfo.getTransactionStatus().isNewTransaction()) {
                    completeTransactionAfterThrowing(txInfo, ex);
                }
                throw ex;
            }
            finally {
                cleanupTransactionInfo(txInfo);
            }
            commitTransactionAfterReturning(txInfo);
            return retVal;
        }

        else {
            // It's a CallbackPreferringPlatformTransactionManager: pass a TransactionCallback in.
            try {
                Object result = ((CallbackPreferringPlatformTransactionManager) tm).execute(txAttr,
                        new TransactionCallback<Object>() {
                            @Override
                            public Object doInTransaction(TransactionStatus status) {
                                TransactionInfo txInfo = prepareTransactionInfo(tm, txAttr, joinpointIdentification, status);
                                try {
                                    return invocation.proceedWithInvocation();
                                }
                                catch (Throwable ex) {
                                    if (txAttr.rollbackOn(ex)) {
                                        // A RuntimeException: will lead to a rollback.
                                        if (ex instanceof RuntimeException) {
                                            throw (RuntimeException) ex;
                                        }
                                        else {
                                            throw new ThrowableHolderException(ex);
                                        }
                                    }
                                    else {
                                        // A normal return value: will lead to a commit.
                                        return new ThrowableHolder(ex);
                                    }
                                }
                                finally {
                                    cleanupTransactionInfo(txInfo);
                                }
                            }
                        });

                // Check result: It might indicate a Throwable to rethrow.
                if (result instanceof ThrowableHolder) {
                    throw ((ThrowableHolder) result).getThrowable();
                }
                else {
                    return result;
                }
            }
            catch (ThrowableHolderException ex) {
                throw ex.getCause();
            }
        }
    }


    private static class ThrowableHolderException extends RuntimeException {

        public ThrowableHolderException(Throwable throwable) {
            super(throwable);
        }

        @Override
        public String toString() {
            return getCause().toString();
        }
    }

    private static class ThrowableHolder {

        private final Throwable throwable;

        public ThrowableHolder(Throwable throwable) {
            this.throwable = throwable;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }
    }

}
