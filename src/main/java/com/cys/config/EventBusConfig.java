package com.cys.config;

import com.cys.eventbus.InnoSubscriberExceptionHandler;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

/**
 * Created by liyuan on 2018/3/1.
 */
@Configuration
public class EventBusConfig {
    private static final SubscriberExceptionHandler EXCEPTION_HANDLER = new InnoSubscriberExceptionHandler();

    public EventBusConfig() {
    }

    @Bean(
            name = {"eventbus"},
            autowire = Autowire.BY_NAME
    )
    public EventBus createEventBus() {
        return new EventBus(EXCEPTION_HANDLER);
    }

    @Bean
    public AsyncEventBus createAsyncEventBus() {
        return new AsyncEventBus(Executors.newWorkStealingPool(), EXCEPTION_HANDLER);
    }
}
