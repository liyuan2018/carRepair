package com.cys.support.mybatis;

/**
 * Created by liyuan on 2018/1/24.
 */

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * mybatis结果集特殊处理转出jpa-page对象
 * @author linliangkun
 * @date 15/8/24
 */
public class PageWrapper implements ObjectWrapper {

    private Object object;

    public PageWrapper(MetaObject metaObject, Object object) {
        this.object = object;
    }

    public Object get(PropertyTokenizer prop) {
        throw new UnsupportedOperationException();
    }

    public void set(PropertyTokenizer prop, Object value) {
        throw new UnsupportedOperationException();
    }

    public String findProperty(String name, boolean useCamelCaseMapping) {
        throw new UnsupportedOperationException();
    }

    public String[] getGetterNames() {
        throw new UnsupportedOperationException();
    }

    public String[] getSetterNames() {
        throw new UnsupportedOperationException();
    }

    public Class<?> getSetterType(String name) {
        throw new UnsupportedOperationException();
    }

    public Class<?> getGetterType(String name) {
        throw new UnsupportedOperationException();
    }

    public boolean hasSetter(String name) {
        throw new UnsupportedOperationException();
    }

    public boolean hasGetter(String name) {
        throw new UnsupportedOperationException();
    }

    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        throw new UnsupportedOperationException();
    }

    public boolean isCollection() {
        return true;
    }

    public void add(Object element) {
        //  object.add(element);
    }

    public <E> void addAll(List<E> element) {
        Page page = (Page)object;
        PageInfo  pageInfo = new PageInfo(element);
        page.getContent().add(pageInfo);
    }

}
