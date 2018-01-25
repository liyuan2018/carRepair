package com.cys.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by liyuan on 2018/1/24.
 */
@Component
public class SystemConfig {
    private static Properties props;

    public SystemConfig() {
        try {
            ClassPathResource e = new ClassPathResource("application.properties");
            props = PropertiesLoaderUtils.loadProperties(e);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static String getProperty(String key) {
        return props == null?null:props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props == null?null:props.getProperty(key, defaultValue);
    }

    public static Properties getProperties() {
        return props;
    }
}
