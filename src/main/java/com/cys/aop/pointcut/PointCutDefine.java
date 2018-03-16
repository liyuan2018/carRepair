package com.cys.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liyuan on 2018/3/16.
 */

public class PointCutDefine {
    public static final String MYBATIS_MAPPER_EXECUTION = "execution(* com.cys..mapper..find*(..))  ||  execution(* com.cys..mybatis..find*(..))";

    public PointCutDefine() {
    }

    @Pointcut("execution(* com.cys..mapper..find*(..))  ||  execution(* com.cys..dao..find*(..))")
    public void aroundMybatisPaginationQuery() {
    }
}