package com.cys.exception;

import com.cys.constants.IMessage;

/**
 * Created by liyuan on 2018/3/16.
 */
public class ResourceIdentifyNotConfigException extends SystemException {
    public ResourceIdentifyNotConfigException(String message) {
        super(message);
    }

    public ResourceIdentifyNotConfigException(IMessage errorCode) {
        super(errorCode);
    }

    public ResourceIdentifyNotConfigException(IMessage errorCode, Object[] msgArgs) {
        super(errorCode, msgArgs);
    }
}

