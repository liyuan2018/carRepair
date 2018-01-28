package com.cys.service.impl;

/**
 * 输出到文件
 * Created by liyuan on 2018/1/29.
 */

import com.cys.model.LoggerModel;
import com.cys.observer.TargetFactory;
import com.cys.service.ILoggerTarget;
import com.cys.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
public class LoggerFile implements ILoggerTarget {
    private static final Logger logger = LoggerFactory.getLogger(TargetFactory.FILE);

    @Override
    public void error(LoggerModel model) {
        MDC.put("user", model.getUserName());
        MDC.put("date", DateUtils.getCurrentDay());
        logger.error(model.getMessage());
    }

    @Override
    public void info(LoggerModel model) {
        MDC.put("user", model.getUserName());
        MDC.put("date", DateUtils.getNowTime());
        logger.info(model.getMessage());
    }

    @Override
    public String getType() {
        return TargetFactory.FILE;
    }
}

