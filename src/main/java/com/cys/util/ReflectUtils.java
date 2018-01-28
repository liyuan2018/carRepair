package com.cys.util;

/**
 * Created by liyuan on 2018/1/28.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtils {

    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);


    /**
     * 利用反射获取指定对象的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtils.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     *
     * @param obj       目标对象
     * @param fieldName 目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     *
     * @param obj        目标对象
     * @param fieldName  目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,
                                     String fieldValue) {
        Field field = ReflectUtils.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 缓存方法
     */
    private static final Map<Class<?>, Method[]> METHODS_CACHEMAP = new HashMap<Class<?>, Method[]>();

    /**
     * 反射 取值、设值,合并两个对象(Field same only )
     *
     * @param fromobj
     * @param toobj
     */
    public static <T> void copyProperties(T fromobj, T toobj,
                                          String... fieldspec) {
        for (String filename : fieldspec) {
            Object val = invokeGetterMethod(fromobj, filename);
            invokeSetterMethod(toobj, filename, val);
        }

    }

    /**
     * 调用Getter方法
     *
     * @param obj          对象
     * @param propertyName 属性名
     * @return
     */
    public static Object invokeGetterMethod(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, null, null);
    }

    /**
     * 调用Setter方法,不指定参数的类型
     *
     * @param obj
     * @param propertyName
     * @param value
     */
    public static void invokeSetterMethod(Object obj, String propertyName,
                                          Object value) {
        invokeSetterMethod(obj, propertyName, value, null);
    }

    /**
     * 调用Setter方法,指定参数的类型
     *
     * @param obj
     * @param propertyName 字段名
     * @param value
     * @param propertyType 为空，则取value的Class
     */
    public static void invokeSetterMethod(Object obj, String propertyName,
                                          Object value, Class<?> propertyType) {
        value = handleValueType(obj, propertyName, value, propertyType);
        propertyType = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class<?>[]{propertyType},
                new Object[]{value});
    }

    private static Object handleValueType(Object obj, String propertyName,
                                          Object value, Class<?> propertyType) {
        String getterMethodName = (propertyType == boolean.class) ? "is"
                + StringUtils.capitalize(propertyName)
                : "get" + StringUtils.capitalize(propertyName);
        Class<?> argsType = value.getClass();
        Class<?> returnType = obtainAccessibleMethod(obj, getterMethodName)
                .getReturnType();
        if (argsType == returnType) {
            return value;
        }
        if (returnType == boolean.class || returnType == Boolean.class) {
            // String temp = value.toString();
            value = Boolean.valueOf(value.toString());
            // (StringUtils.isNotBlank(temp) && Long.valueOf(temp) > 0) ? true
            // : false;
        } else if (returnType == long.class || returnType == Long.class) {
            value = Long.valueOf(value.toString());
        } else if (returnType == double.class || returnType == Double.class) {
            value = Double.valueOf(value.toString());
        } else if (returnType == float.class || returnType == Float.class) {
            value = Float.valueOf(value.toString());
        } else if (returnType == Date.class) {
            if (argsType == java.sql.Date.class) {
                value = new Date(((java.sql.Date) value).getTime());
            } else if (argsType == Timestamp.class) {
                value = new Date(((Timestamp) value).getTime());
            }
        } else if (returnType == short.class || returnType == Short.class) {
            value = Short.valueOf(value.toString());
        } else if (returnType == BigDecimal.class) {
            value = BigDecimal.valueOf(Long.valueOf(value.toString()));
        } else if (returnType == BigInteger.class) {
            value = BigInteger.valueOf(Long.valueOf(value.toString()));
        } else if (returnType == String.class) {
            value = String.valueOf(value);
        } else if (returnType == int.class || returnType == Integer.class) {
            value = Integer.valueOf(value.toString());
        }
        return value;
    }

    /**
     * 直接调用对象方法，忽视private/protected修饰符
     *
     * @param obj
     * @param methodName
     * @param parameterTypes
     * @param args
     * @return
     */
    public static Object invokeMethod(final Object obj,
                                      final String methodName, final Class<?>[] parameterTypes,
                                      final Object[] args) {
        Method method = obtainAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException(
                    "Devkit: Could not find method [" + methodName
                            + "] on target [" + obj + "].");
        }
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 循环向上转型，获取对象的DeclaredMethod,并强制设置为可访问 如向上转型到Object仍无法找到，返回null
     * <p/>
     * 用于方法需要被多次调用的情况，先使用本函数先取得Method,然后调用Method.invoke(Object obj,Object...
     * args)
     *
     * @param obj
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method obtainAccessibleMethod(final Object obj,
                                                final String methodName, final Class<?>... parameterTypes) {
        Class<?> superClass = obj.getClass();
        Class<Object> objClass = Object.class;
        for (; superClass != objClass; superClass = superClass.getSuperclass()) {
            Method method = null;
            try {
                method = superClass.getDeclaredMethod(methodName,
                        parameterTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                // Method不在当前类定义，向上转型
            } catch (SecurityException e) {
                // Method不在当前类定义，向上转型
            }
        }
        return null;
    }

    /**
     * 不能确定方法是否包含参数时，通过方法名匹配获得方法
     *
     * @param obj
     * @param methodName
     * @return
     */
    public static Method obtainMethod(final Object obj, final String methodName) {
        Class<?> clazz = obj.getClass();
        Method[] methods = METHODS_CACHEMAP.get(clazz);
        if (methods == null) { // 尚未缓存
            methods = clazz.getDeclaredMethods();
            METHODS_CACHEMAP.put(clazz, methods);
        }
        for (Method method : methods) {
            if (method.getName().equals(methodName))
                return method;
        }
        return null;

    }

    /**
     * 直接读取对象属性值 忽视private/protected修饰符，不经过getter函数
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object obtainFieldValue(final Object obj,
                                          final String fieldName) {
        Field field = obtainAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Devkit: could not find field ["
                    + fieldName + "] on target [" + obj + "]");
        }
        Object retval = null;
        try {
            retval = field.get(obj);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return retval;

    }

    /**
     * 直接设置对象属性值 忽视private/protected修饰符，不经过setter函数
     *
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(final Object obj, final String fieldName,
                                     final Object value) {
        Field field = obtainAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Devkit: could not find field ["
                    + fieldName + "] on target [" + obj + "]");
        }
        try {
            field.set(obj, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环向上转型，获取对象的DeclaredField,并强制设为可访问 如向上转型Object仍无法找到，返回null
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field obtainAccessibleField(final Object obj,
                                              final String fieldName) {
        Class<?> superClass = obj.getClass();
        Class<Object> objClass = Object.class;
        for (; superClass != objClass; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义，向上转型
            } catch (SecurityException e) {
                // Field不在当前类定义，向上转型
            }
        }
        return null;
    }

    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if ((e instanceof IllegalAccessException) || (e instanceof IllegalArgumentException)
                || (e instanceof NoSuchMethodException)) {
            return new IllegalArgumentException(e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

    /**
     * 获得超类的参数类型，取第一个参数类型
     * @param <T> 类型参数
     * @param clazz 超类类型
     */
    @SuppressWarnings("rawtypes")
    public static <T> Class<T> getClassGenricType(final Class clazz) {
        return getClassGenricType(clazz, 0);
    }

    /**
     * 根据索引获得超类的参数类型
     * @param clazz 超类类型
     * @param index 索引
     */
    @SuppressWarnings("rawtypes")
    public static Class getClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

}

