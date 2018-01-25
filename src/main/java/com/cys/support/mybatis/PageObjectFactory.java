package com.cys.support.mybatis;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by liyuan on 2018/1/24.
 */
public class PageObjectFactory extends DefaultObjectFactory {

    public static Logger logger = LoggerFactory.getLogger(PageObjectFactory
            .class);

    @Override
    public Object create(Class type) {
        if(type.getName().equals("org.springframework.data.domain.Page")) {
            try {
                Page page = new CustomPageImpl(new ArrayList<>());
                return page;
            }
            catch (Exception ex){
                logger.error(ex.getMessage());
                return  null;
            }
        }
        else
            return super.create(type);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        if(type.getName().equals("org.springframework.data.domain.Page")){
            return true;
        }
        else  return Collection.class.isAssignableFrom(type);
    }

}
