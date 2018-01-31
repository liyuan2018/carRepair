package com.cys.common.enums;

/**
 * Created by liyuan on 2018/1/31.
 */
public enum StatusEnum {
    /** 0：已删除 */
    DELETED("0","已删除"),
    /** 1：启用 */
    ENABLE("1","启用"),
    /** 2：禁用 */
    DISABLE("2","禁用");

    /** 状态值 */
    private final String code;

    private final String name;

    private StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getName() {
        return name;
    }
}
