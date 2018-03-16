package com.cys.annotation.mybatis;

/**
 * Created by liyuan on 2018/3/16.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * MyBatis Repository注解
 * (通过{@link org.mybatis.spring.mapper.MapperScannerConfigurer}扫描)
 *
 * @author from springside4
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
    String value() default "";
}