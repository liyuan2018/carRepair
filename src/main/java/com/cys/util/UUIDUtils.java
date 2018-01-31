package com.cys.util;

/**
 * Created by liyuan on 2018/1/29.
 */
/**
 * 文件名：UUIDUtil.java
 *
 * 版本信息：
 * 日期：2012-4-20
 * Copyright  Corporation 2012
 * 版权所有 E-vada
 *
 */

import java.util.UUID;

public class UUIDUtils {

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

//    public static void main( String[] args ) throws Exception {
//        for ( int i=0; i<100; i++) {
//            String id =UUID.randomUUID().toString().replaceAll("-","");
//            System.out.println(id+","+id.length());
//        }
//
//    }
}


