package com.cys.web.converter;

import com.cys.common.domain.ResultData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 转换器接口
 * Created by liyuan on 2018/1/31.
 */
public abstract class AbstractConverter {
    /**
     * 待转换的实体所在包
     */
    protected static final String PACKAGE_TO_PROCESS = "com.evada";
    /**
     * 用以返回的空实例
     */
    protected static final Map EMPTY = Collections.unmodifiableMap(new HashMap(1));
    private static final Logger logger = LogManager.getLogger(AbstractConverter.class.getName());

    /**
     * 转成对应格式对象
     *
     * @param object controller传入的响应对象
     * @return
     */
    public final Object convert(Object object) {
        if (object instanceof Collection && ((Collection) object).size() == 0) { // 集合为空
            logger.debug("response 内容为空集合，返回EMPTY Map: {}");
            return EMPTY;
        }

        // 不处理Map，使用ResultData可以替代map，不需要单复数手动转换。
        if (object instanceof Map) {
            logger.debug("响应内容为Map, 开始处理。");
            return extractMap((Map) object);
        } else if (object instanceof List) {
            logger.debug("响应内容为List, 开始处理。");
            return extractList((List) object);
        } else if (object instanceof Set) {
            logger.debug("响应内容为Set, 开始处理。");
            return extractSet((Set) object);
        } else if (ResultData.class.equals(object.getClass())) {
            logger.debug("响应内容为PageResource, 开始处理。");
            return ((ResultData) object).getEntities();
        } else if (object.getClass().getName().startsWith(PACKAGE_TO_PROCESS)) {
            logger.debug("响应内容为{"+PACKAGE_TO_PROCESS+"} 下面的实体, 开始处理。");
            return extractEntity(object);
        }
        return object;
    }

    /** 抽取Map进行处理 */
    public abstract Object extractMap(Map map);

    /**
     * 抽取List进行处理
     */
    public abstract Object extractList(List list);

    /**
     * 抽取Set进行处理
     */
    public abstract Object extractSet(Set set);

    /**
     * 抽取com.evada包下的实体进行处理
     */
    public abstract Object extractEntity(Object object);
}
