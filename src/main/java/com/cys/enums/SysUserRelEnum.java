package com.cys.enums;

/**
 * Created by liyuan on 2018/3/1.
 */
public enum SysUserRelEnum {
    BUSINESS_LICENSE("1","营业执照"),
    DOOR_HEAD_IMG("2","门头照");
    private final String code;

    private final String name;

    private SysUserRelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
