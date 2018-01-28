package com.cys.util;

/**
 * 参数工具类
 * Created by liyuan on 2018/1/28.
 */

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamUtils {

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value.trim());
    }


    /**
     * 判断字符串数组是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(String[] value) {
        return value == null || value.length <= 0;
    }

    /**
     * 判断文件数组是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(File[] value) {
        return value == null || value.length <= 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(int[] value) {
        return value == null || value.length == 0;
    }

    /**
     * 判断字符串数组是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(double[] value) {
        return value == null || value.length == 0;
    }

    /**
     * 判断字符串数组是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(char[] value) {
        return value == null || value.length == 0;
    }

    /**
     * 判断List是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(List<?> value) {
        return value == null || value.size() == 0;
    }

    /**
     * 判断List是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isEmpty(Map<?, ?> value) {
        return value == null || value.size() == 0;
    }

    /**
     * 判断两个对象是否相同
     *
     * @param value1
     * @param value2
     * @return
     */
    public static boolean isEqual(Object value1, Object value2) {
        if (isNull(value1) || isNull(value2)) {
            return false;
        }

        return value1.equals(value2);
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    /**
     * 判断数组对象是否为空
     *
     * @param value
     * @return true 是,false 否
     */
    public static boolean isNull(Object[] value) {
        return value == null || value.length <= 0;
    }

    //StringUtils.isNumber(String str);

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return true 是,false 否
     */
    public static boolean isNumeric(String str) {
//		Pattern pattern = Pattern.compile("[0-9]*");
//		Matcher isNum = pattern.matcher(str);
//		if (!isNum.matches()) {
//			return false;
//		}
        //TODO
        return true;
        //return StringUtils.isNumeric(str);
    }

    /**
     * String 和 Integer 转换
     *
     * @param value
     * @return
     */
    public static int s2i(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * String 和 Integer 转换
     *
     * @param value
     * @return
     */
    public static String i2s(int value) {
        return String.valueOf(value);
    }

    /**
     * String 和 Long 转换
     *
     * @param value
     * @return
     */
    public static Long s2l(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return 0l;
        }
    }

    /**
     * String 和 Long 转换
     *
     * @param value
     * @return
     */
    public static String l2s(long value) {
        return String.valueOf(value);
    }

    /**
     * String 转换为 InputStream
     *
     * @param str
     * @return
     */
    public static InputStream String2InputStream(String str) {
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

    /**
     * InputStream 转换为 String
     *
     * @param is
     * @return
     * @throws java.io.IOException
     */
    public static String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * 贪婪匹配
     *
     * @param str
     * @param exp 输入的匹配条件 eg：paramString =a*b  str =...a...b..这种格式都是匹配的
     * @return
     */
    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        String regex = exp;
        if (exp.indexOf("*") != -1) {
            regex = exp.replaceAll("\\*", ".*");
        }
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        }
        return false;
    }
}

