package com.cys.observer;

/**
 * 根据配置文件获取输出的位置
 * Created by liyuan on 2018/1/28.
 */

import com.cys.config.LoggerConstants;
import com.cys.model.ConfigModel;
import com.cys.service.ILoggerTarget;
import com.cys.service.impl.LoggerConsole;
import com.cys.service.impl.LoggerDb;
import com.cys.service.impl.LoggerFile;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TargetFactory {
    public static String CONSOLE = "console";
    public static String DB = "db";
    public static String FILE = "file";
    private Map<String, List<ILoggerTarget>> cache = new HashMap<>();

    private static class Singleton {
        private  static final TargetFactory INSTANCE = new TargetFactory();
    }

    public static TargetFactory getFactory() {
        return Singleton.INSTANCE;
    }

    public List<ILoggerTarget> getAllTargets(String loggerOut) {
        if (StringUtils.isBlank(loggerOut)) {
            loggerOut = LoggerConstants.LOGGER_OUT;
        }

        List<ILoggerTarget> list = new ArrayList<>();

        if (loggerOut.contains(CONSOLE)) {
            list.add(new LoggerConsole());
        }

        if (loggerOut.contains(DB)) {
            list.add(new LoggerDb());
        }

        if (loggerOut.contains(FILE)) {
            list.add(new LoggerFile());
        }

        return list;
    }

    /**
     * 根据正则匹配哪个模块输出到哪里，如果没有配置，而又有loggerinfo标签，
     * 那么默认输出到logger_out配置的地方。
     * @param className 输出的类
     * @return 当前注解应该输出的地方列表
     */
    public List<ILoggerTarget> getTargets(String className) {
        List<ILoggerTarget> list = cache.get(className);

        if (null == list) {
            List<ConfigModel> props = LoggerConstants.props;
            String key, value;
            boolean hasContain;
            for (ConfigModel m : props) {
                key = m.getKey();
                key = key.replaceAll("(?<!\\.\\.)\\*", "\\\\w*");
                hasContain = Pattern.matches(key, className);
                if (hasContain) {
                    value = m.getValue();
                    list = getAllTargets(value);
                    break;
                }
            }

            if (null == list) {
                list = getAllTargets(null);
            }

            cache.put(className, list);
        }

        return list;
    }

}
