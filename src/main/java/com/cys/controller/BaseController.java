package com.cys.controller;

/**
 * 控制器基类
 * Created by liyuan on 2018/1/28.
 */

import com.cys.common.base.ModelClassMapLoader;
import com.cys.model.LoggerModel;
import com.cys.model.SysUser;
import com.cys.observer.TargetObserver;
import com.cys.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    private long loggerStartTime;

    @Autowired
    protected ModelClassMapLoader modelClassMapLoader;

    public BaseController(){
        loggerStartTime = System.currentTimeMillis();
    }

    public long getLoggerStartTime() {
        return loggerStartTime;
    }

    public void operLogger(Class clazz, LoggerModel loggerModel){
        loggerModel.setClassName(clazz.getName());
        long time = System.currentTimeMillis() - getLoggerStartTime();
        loggerModel.setUsedTime(time);
        loggerModel.setIp(getIp());
        loggerModel.setUserName(getUserName());
        loggerModel.setUserId(getUserId());
        //输出日志
        TargetObserver.getInstance().info(loggerModel);
    }

    public void operLogger(Class clazz, List<LoggerModel> loggerModels){
        long time = System.currentTimeMillis() - getLoggerStartTime();
        for (LoggerModel loggerModel : loggerModels) {
            loggerModel.setClassName(clazz.getName());
            loggerModel.setUsedTime(time);
            loggerModel.setIp(getIp());
            loggerModel.setUserName(getUserName());
            loggerModel.setUserId(getUserId());
            //输出日志
            TargetObserver.getInstance().info(loggerModel);
        }
    }

    private String getIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        return ip;
    }

    private String getUserName() {
        SysUser user = SessionUtils.getCurrentUser();
        if (null != user) {
            return user.getName();
        }
        return "";
    }

    private String getUserId() {
        SysUser user = SessionUtils.getCurrentUser();
        if (null != user) {
            return user.getId();
        }
        return "";
    }
}
