package com.cys.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * 上下文工具类
 * Created by liyuan on 2018/1/24.
 */
@Configuration
public class ApplicationContextUtils implements ApplicationContextAware,ServletContextAware {
    private static ApplicationContext context;
    private static ServletContext servletContext;

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        this.context = arg0;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }


    public static ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setServletContext(ServletContext arg0) {
        servletContext = arg0;
    }

    public static String getRealPath(String path) {
        return servletContext.getRealPath(path);
    }

    public static String getClassesPath() {
        String path = StringUtils.trimSufffix(getRealPath("/"), File.separator) + "\\WEB-INF\\classes\\".replace("\\", File.separator);
        return path;
    }

    public static String getRootPath() {
        String rootPath = StringUtils.trimSufffix(getRealPath("/"), File.separator) + File.separator;
        return rootPath;
    }
}
