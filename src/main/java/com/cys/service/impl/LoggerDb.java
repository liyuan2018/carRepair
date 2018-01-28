package com.cys.service.impl;

/**
 * 输出到数据库
 * Created by liyuan on 2018/1/29.
 */

import com.cys.model.LoggerModel;
import com.cys.observer.TargetFactory;
import com.cys.service.ILoggerTarget;
import com.cys.util.DateUtils;
import com.cys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggerDb implements ILoggerTarget {
    private static final Logger logger = LoggerFactory.getLogger(TargetFactory.DB);

    @Override
    public void error(LoggerModel model) {
        MDC.put("id", UUIDUtils.generate());
        MDC.put("principalId", model.getPrincipalId());
        MDC.put("principalType", model.getPrincipalType());
        MDC.put("userId", model.getUserId());
        MDC.put("createTime", DateUtils.getNowTime());
        MDC.put("ip", model.getIp());
        MDC.put("usedTime", String.valueOf(model.getUsedTime()));
        MDC.put("event", model.getEvt().toString());
        MDC.put("type", model.getType());
        MDC.put("operateObject", model.getOperateObject());
        logger.error(model.getMessage());
    }

    @Override
    public void info(LoggerModel model) {
        MDC.put("id", UUIDUtils.generate());
        MDC.put("principalId", model.getPrincipalId());
        MDC.put("principalType", model.getPrincipalType());
        MDC.put("userId", model.getUserId());
        MDC.put("createTime", DateUtils.getNowTime());
        MDC.put("ip", model.getIp());
        MDC.put("usedTime", String.valueOf(model.getUsedTime()));
        MDC.put("event", model.getEvt().toString());
        MDC.put("type", model.getType());
        MDC.put("operateObject", model.getOperateObject());
        logger.info(model.getMessage());
    }

    @Override
    public String getType() {
        return TargetFactory.DB;
    }

}

