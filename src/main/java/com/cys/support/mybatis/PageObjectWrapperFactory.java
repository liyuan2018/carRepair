package com.cys.support.mybatis;



import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

/**
 * mybatis结果集特殊处理转出jpa-page对象
 * Created by liyuan on 2018/1/24.
 */
public class PageObjectWrapperFactory extends DefaultObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        if(object.getClass().getName().equals("com.cys.support.mybatis.CustomPageImpl"))
            return true;
        else return false;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        if(object.getClass().getName().equals("com.cys.support.mybatis.CustomPageImpl"))
            return   new PageWrapper(metaObject, object);
        else
            throw new ReflectionException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
    }
}
