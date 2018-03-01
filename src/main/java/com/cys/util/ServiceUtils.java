package com.cys.util;

import com.cys.exception.SystemException;
import com.cys.model.BaseRelationModel;
import com.cys.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by liyuan on 2018/3/1.
 */
public class ServiceUtils<T extends BaseRelationModel, ID extends Serializable> {

    public static final Logger logger = LoggerFactory
            .getLogger(ServiceUtils.class);

    /**
     * 获取目标模型对应的service
     *
     * @param targetType
     *            目标模型类型
     * @return IBaseService
     */
    public static IBaseService getTargetService(String targetType) {
        String targetServiceName = ClassUtils
                .getLowerCamelAndSingularize(targetType) + "Service";
        IBaseService targetService = SpringUtils.getBean(targetServiceName);
        if (targetService == null) {
            logger.error("can't find principalService : " + targetServiceName);
            throw new SystemException("can't find principalService : "
                    + targetServiceName);
        }
        return targetService;
    }
}
