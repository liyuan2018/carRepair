package com.cys.eventbus;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liyuan on 2018/3/1.
 */
public class InnoSubscriberExceptionHandler implements SubscriberExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InnoSubscriberExceptionHandler.class);

    public InnoSubscriberExceptionHandler() {
    }

    public void handleException(Throwable exception, SubscriberExceptionContext context) {
        LOGGER.error(exception.getMessage(), exception);
    }
}
