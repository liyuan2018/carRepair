package com.cys.exception;

/**
 * Created by liyuan on 2018/1/28.
 */

import com.cys.constants.IMessage;
import com.cys.util.I18nUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2332608236621015980L;

    private IMessage errorCode;

    private String type = "B-";

    private Object[] msgArgs;

    private Object data;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(IMessage errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(IMessage errorCode,Object data) {
        this.data=data;
        this.errorCode = errorCode;
    }


    public BusinessException(IMessage errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BusinessException(IMessage errorCode, Object[] msgArgs) {
        this.errorCode = errorCode;
        this.msgArgs = msgArgs;
    }

    public BusinessException(IMessage errorCode, Object[] msgArgs, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.msgArgs = msgArgs;
    }


    public Object[] getMsgArgs() {
        return msgArgs;
    }

    public void setMsgArgs(Object[] msgArgs) {
        this.msgArgs = msgArgs;
    }

    public String getMessage() {
        String msg = this.getMsg();
        if(StringUtils.isEmpty(msg)){
            return super.getMessage();
        }
        else {
            return msg;
        }
    }

    public String getSuperMessage(){
        return super.getMessage();
    }

    /**
     * 获得出错信息. 读取i18N properties文件中Error Code对应的message,并组合参数获得i18n的出错信息.
     */
    public String getMsg() {
        // 如果errorMessage不为空,直接返回出错信息.
        String msg = "";
        if (this.errorCode==null) {
            msg = super.getMessage();
            return msg;
        }
        try {
            msg = I18nUtils.getMessage(this.errorCode, this.getMsgArgs());
        } catch (Exception ex) {
            msg = MessageFormat.format("错误代码: {0}, 错误参数: {1}, 国际化消息读取失败!",
                    this.errorCode.getCode(), StringUtils.join(this.getMsgArgs(), "|"));
        }
        return msg;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IMessage getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(IMessage errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

