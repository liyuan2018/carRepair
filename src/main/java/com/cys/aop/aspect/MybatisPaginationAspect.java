package com.cys.aop.aspect;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created by liyuan on 2018/3/16.
 */
@Aspect
@Component
public class MybatisPaginationAspect {
    public static Logger logger = LoggerFactory.getLogger(MybatisPaginationAspect.class);

    public MybatisPaginationAspect() {
    }

    @Around("com.cys.aop.pointcut.PointCutDefine.aroundMybatisPaginationQuery()")
    public Object aroundMybatisQuery(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Pageable pageable = null;
        Object[] retVal = args;
        int page = args.length;

        for(int pageInfo = 0; pageInfo < page; ++pageInfo) {
            Object result = retVal[pageInfo];
            if(result instanceof Pageable && result != null) {
                logger.debug("mybatis拦截查询分页处理开始");
                pageable = (Pageable)result;
                PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
            }
        }

        Object var8 = pjp.proceed();
        if(var8 instanceof Page) {
            Page var9 = (Page)var8;
            if(var9.getContent().size() == 1 && var9.getContent().get(0) instanceof PageInfo) {
                PageInfo var10 = (PageInfo)((PageInfo)var9.getContent().get(0));
                PageImpl var11 = new PageImpl(var10.getList(), pageable, var10.getTotal());
                logger.debug("mybatis拦截查询分页处理结束");
                return var11;
            } else {
                return var8;
            }
        } else {
            return var8;
        }
    }
}
