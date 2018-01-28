package com.cys.config;

/**
 * Created by liyuan on 2018/1/28.
 */

import com.cys.model.ConfigModel;

import java.util.*;


public class LoggerConstants {
    /**配置需要将信息输出到控制台，文件，还是数据库**/
    public static String LOGGER_OUT;
    public static List<ConfigModel> props = new ArrayList<>();

    private static ResourceBundle CONSTANT = ResourceBundle.getBundle("logger");

    static {
        LOGGER_OUT = CONSTANT.getString("logger.default.out");
        initProps();
    }

    private static void initProps() {
        Enumeration<String> keys = CONSTANT.getKeys();
        String key,value;
        ConfigModel m;
        String[] ks;
        int index=0;
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            if (!"logger.default.out".equals(key)) {
                value = CONSTANT.getString(key);
                if (key.indexOf("||") > 0) {
                    ks = key.split("||");
                    key = ks[1];
                    index = Integer.parseInt(ks[0]);
                }

                m = new ConfigModel(key, value, index);
                props.add(m);
            }
        }

        Collections.sort(props);
    }
}

