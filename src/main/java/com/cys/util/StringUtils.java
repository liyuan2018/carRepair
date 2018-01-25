package com.cys.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liyuan on 2018/1/24.
 */
public class StringUtils {
    private static final String getMethodName = "get";

    public StringUtils() {
    }

    public static String asString(Object obj) {
        return obj != null?obj.toString():"";
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().length() == 0;
    }

    public static String join(String[] list, String joinStr) {
        StringBuffer s = new StringBuffer();

        for(int i = 0; list != null && i < list.length; ++i) {
            if(i + 1 == list.length) {
                s.append(list[i]);
            } else {
                s.append(list[i]).append(joinStr);
            }
        }

        return s.toString();
    }

    public static String firstCharLowerCase(String s) {
        return s != null && !"".equals(s)?s.substring(0, 1).toLowerCase() + s.substring(1):"";
    }

    public static String firstCharUpperCase(String s) {
        return s != null && !"".equals(s)?s.substring(0, 1).toUpperCase() + s.substring(1):"";
    }

    public static String toBeanPatternStr(String src) {
        String dist = src.toLowerCase();
        Pattern pattern = Pattern.compile("_([a-z0-9])");

        for(Matcher matcher = pattern.matcher(dist); matcher.find(); dist = dist.replaceFirst(matcher.group(0), matcher.group(1).toUpperCase())) {
            ;
        }

        return dist;
    }

    public static String toJSLineSeparateStr(String src) {
        if(src == null) {
            return "";
        } else {
            String dist = src.replaceAll("\r\n", "\\\\n");
            dist = dist.replaceAll("\r", "\\\\n");
            dist = dist.replaceAll("\n", "\\\\n");
            return dist;
        }
    }

    public static String getBytesString(String input, String code) {
        try {
            byte[] e = input.getBytes(code);
            return Arrays.toString(e);
        } catch (UnsupportedEncodingException var3) {
            return String.valueOf(code.hashCode());
        }
    }

    public static String getStringFromClob(Clob clob) {
        String result = "";

        try {
            if(clob == null) {
                return null;
            }

            Reader reader = clob.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            StringBuffer sb;
            for(sb = new StringBuffer(1024); line != null; line = br.readLine()) {
                sb.append(line);
            }

            result = sb.toString();
        } catch (Exception var6) {
            ;
        }

        return result;
    }

    public static String formatParamMsg(String message, Object[] args) {
        for(int i = 0; i < args.length; ++i) {
            message = message.replace("{" + i + "}", args[i].toString());
        }

        return message;
    }

    public static String trimPrefix(String toTrim, String trimStr) {
        while(toTrim.startsWith(trimStr)) {
            toTrim = toTrim.substring(trimStr.length());
        }

        return toTrim;
    }

    public static String trimSufffix(String toTrim, String trimStr) {
        while(toTrim.endsWith(trimStr)) {
            toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
        }

        return toTrim;
    }

    public static String capitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0?(new StringBuffer(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString():str;
    }

    /** @deprecated */
    public static String capitalise(String str) {
        return capitalize(str);
    }

    public static String uncapitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0?(new StringBuffer(strLen)).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString():str;
    }

    /** @deprecated */
    public static String uncapitalise(String str) {
        return uncapitalize(str);
    }

    public static String getFieldMethodName(String dateFieldName) {
        return "get" + firstLetterToUpper(dateFieldName);
    }

    public static String firstLetterToUpper(String dateFieldName) {
        char[] cs = dateFieldName.toCharArray();
        cs[0] = (char)(cs[0] - 32);
        return String.valueOf(cs);
    }
}