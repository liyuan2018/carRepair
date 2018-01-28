package com.cys.service;

import com.cys.model.LoggerModel;

/**
 * Created by liyuan on 2018/1/28.
 */

public interface ILoggerTarget {
    /**
     * 记录错误信息
     * @param model  信息对象
     */
    void error(LoggerModel model);
    /**
     * 记录业务访问信息
     * @param model 信息对象
     */
    void info(LoggerModel model);

    /**
     * 获取输出类型
     */
    String getType();
}
