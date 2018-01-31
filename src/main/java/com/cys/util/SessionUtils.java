package com.cys.util;

/**
 * Created by liyuan on 2018/1/29.
 */

import com.cys.constants.ErrorCode;
import com.cys.exception.SessionTimeoutException;
import com.cys.model.SysUser;
import com.cys.repository.SysUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class SessionUtils {

    private static Logger logger = LoggerFactory.getLogger(SessionUtils.class);


    private static final String SYSTEM_USER_ACCOUNT="app.core.system_user_account";

    public static SysUser getCurrentUser(){

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(attributes==null){
            Environment environment= (Environment)ApplicationContextUtils.getApplicationContext().getBean("environment");
            if(environment!=null&& environment.getProperty(SYSTEM_USER_ACCOUNT)!=null){
                SysUserRepository userMapper = (SysUserRepository)ApplicationContextUtils.getApplicationContext().getBean("sysUserRepository");
                //后端调用
                SysUser  userInfo =  userMapper.findByAccount(environment.getProperty(SYSTEM_USER_ACCOUNT));
                return userInfo;
            }
            else
                return null;
        }
        else{
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)attributes;
            HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequestAttributes.getRequest());
            Object user = httpServletRequest.getSession().getAttribute("user");
            if(!StringUtils.isEmpty(user)){
                return (SysUser)user;
            }
            else
                return null;
        }
    }


    public static String getCurrentUserId() {

        SysUser userInfo = getCurrentUser();
        if(userInfo!=null){
            return userInfo.getId();
        }
        else{
            throw new SessionTimeoutException(ErrorCode.Common.sessionTimeout);
        }
    }
}

