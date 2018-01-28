package com.cys.util;

import org.mybatis.generator.api.ShellRunner;

/**
 * Created by liyuan on 2018/1/28.
 */
public class ClassGennerator {
    public static void main(String[] args) {
        args = new String[] { "-configfile", "src\\main\\resources\\mybatis\\mybatis-generator-config.xml", "-overwrite" };
        ShellRunner.main(args);
    }
}
