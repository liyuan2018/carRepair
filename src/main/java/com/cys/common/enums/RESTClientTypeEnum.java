package com.cys.common.enums;

import com.cys.util.StringUtils;

/**
 * REST客户端类型枚举
 * Created by liyuan on 2018/1/31.
 */
public enum RESTClientTypeEnum {
    /** ember.js客户端 默认类型*/
    EMBER("ember"),
    /** 移动端 (暂不支持)*/
    MOBILE("mobile");

    /** 客户端名 */
    private String name;

    private RESTClientTypeEnum(String name){
        this.name = name;
    }

    /**
     * 获得客户端类型
     * @param clientName  客户端名
     * @return
     */
    public static RESTClientTypeEnum getByName(String clientName){
        if(StringUtils.isEmpty(clientName)){ // 未设置返回ember类型
            return EMBER;
        }
        for (RESTClientTypeEnum clientType : RESTClientTypeEnum.values()) {
            if(clientType.getName().equalsIgnoreCase(clientName)){
                return clientType;
            }
        }
        return EMBER;
    }

    public String getName() {
        return name;
    }
}
