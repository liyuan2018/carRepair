package com.cys.constants;

/**
 * Created by liyuan on 2018/2/28.
 */
public class HardCode {

    //文件上传路径
    public final  static String UPLOAD_PATH = "C:\\file";
    //文件上传类型
    public final static String STORAGE_TYPE= "1";

    public static enum Separator {
        COMMA(",", "逗号"),
        SHUN_SLASH("/", "顺斜杠"),
        POINT(".", "点"),
        SEMICOLON(";", "分号");

        private final String code;
        private final String name;

        private Separator(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return this.code;
        }
    }

    public static enum Key {
        WEI_XIN_OPEN_ID("openid", "微信openId"),
        WEI_XIN_SESSION_KEY("session_key", "微信sessionKey"),
        WEI_XIN_ERR_CODE("errcode", "微信errcode"),
        WEI_XIN_ERR_MSG("errmsg", "微信errmsg");

        private final String code;
        private final String name;

        private Key(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return this.code;
        }
    }
}
