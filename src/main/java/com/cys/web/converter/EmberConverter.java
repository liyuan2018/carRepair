package com.cys.web.converter;

import com.cys.util.ClassUtils;
import com.cys.util.ParamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

/**
 * ember转换器
 * Created by liyuan on 2018/1/31.
 */
public class EmberConverter extends AbstractConverter {
    private static final Logger logger = LoggerFactory.getLogger(EmberConverter.class.getName());

    private EmberConverter() {
    }

    /**
     * 获得单例
     *
     * @return
     */
    public static EmberConverter getInstance() {
        return EmberConverterHolder.INSTANCE;
    }

    @Override
    public Object extractMap(Map map) {
        final Map<String, Object> results = new HashMap<String, Object>();
        for (Object item : map.entrySet()) {
            Map.Entry entry = (Map.Entry) item;
            if (entry.getValue().getClass().getName().startsWith(PACKAGE_TO_PROCESS)) {
                results.put(ClassUtils.getLowerCamelAndSingularize(entry.getValue().getClass().getSimpleName()), entry.getValue());
                logger.debug("处理实体: {"+entry.getValue().getClass().getSimpleName()+"}");
            } else if (entry.getValue() instanceof List) {
                List list = (List) entry.getValue();
                if (ParamUtils.isEmpty(list)) {
                    return results.put(entry.getKey().toString(), entry.getValue());
                }
                results.put(ClassUtils.getLowerCamelAndPluralize(list.get(0).getClass().getSimpleName()), list);
                logger.debug("处理List: {"+list.get(0).getClass().getSimpleName()+"}");
            } else if (entry.getValue() instanceof Set) {
                logger.debug("准备处理Set。");
                Set set = (Set) entry.getValue();
                if (set.size() == 0) {
                    return results.put(entry.getKey().toString(), entry.getValue());
                }
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    results.put(ClassUtils.getLowerCamelAndPluralize(iterator.next().getClass().getSimpleName()), set);
                    logger.debug("处理Set: {"+iterator.next().getClass().getSimpleName()+"}");
                    break;
                }
            } else {
                results.put(entry.getKey().toString(), entry.getValue());
                logger.debug("处理默认情况, key: {"+entry.getKey().toString()+"}, value: {"+entry.getValue()+"}");
            }
        }
        logger.debug("响应内容为Map, size: {"+ map.size()+"}, 处理完毕。");
        return results;
    }

    @Override
    public Object extractList(List list) {
        final Map<String, Object> results = new HashMap<String, Object>();
        results.put(ClassUtils.getLowerCamelAndPluralize(list.get(0).getClass().getSimpleName()), list);
        logger.debug("响应内容为List, name: {"+list.get(0).getClass().getSimpleName()+"}, 处理完毕。");
        return results;
    }

    @Override
    public Object extractSet(Set set) {
        logger.debug("准备处理响应Set内容。");
        final Map<String, Object> results = new HashMap<String, Object>();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            results.put(ClassUtils.getLowerCamelAndPluralize(iterator.next().getClass().getSimpleName()), set);
            logger.debug("响应内容为Set, name: {"+iterator.next().getClass().getSimpleName()+"}, 处理完毕。");
            break;
        }
        return results;
    }

    @Override
    public Object extractEntity(Object object) {
        Map<String, Object> results = new HashMap<String, Object>();
        results.put(ClassUtils.getLowerCamelAndSingularize(object.getClass().getSimpleName()), object);
        logger.debug("响应内容为实体, name: {"+object.getClass().getSimpleName()+"}, 处理完毕。");
        return results;
    }

    private static class EmberConverterHolder {
        private static final EmberConverter INSTANCE = new EmberConverter();
    }
}
