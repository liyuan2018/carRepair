package com.cys.util;

import org.apache.commons.lang3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by liyuan on 2018/1/24.
 */
public class Base64Utils {
    public Base64Utils() {
    }

    public static String inputStreamToBase64(InputStream input) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean len = false;
        byte[] b = new byte[1024];

        int len1;
        while((len1 = input.read(b, 0, b.length)) != -1) {
            byteArrayOutputStream.write(b, 0, len1);
        }

        byte[] buffer = byteArrayOutputStream.toByteArray();
        String base64 = (new BASE64Encoder()).encode(buffer);
        base64 = org.apache.commons.lang3.StringUtils.replace(org.apache.commons.lang3.StringUtils.replace(base64, "\r", ""), "\n", "");
        byteArrayOutputStream.close();
        input.close();
        return base64;
    }

    public static String getBase64(String s) throws UnsupportedEncodingException {
        byte[] bytes = Base64.encodeBase64(s.getBytes("utf-8"));
        return new String(bytes, "utf-8");
    }

    public static String getFromBase64(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("utf-8");
        byte[] convertBytes = Base64.decodeBase64(bytes);
        return new String(convertBytes, "utf-8");
    }
}
