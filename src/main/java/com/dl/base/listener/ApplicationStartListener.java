package com.dl.base.listener;

import com.dl.base.processor.FeignClientPostProcessor;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 应用事件监听
 *
 * @since 15/01/2018 2:23 PM
 */
public class ApplicationStartListener implements ApplicationListener<ApplicationPreparedEvent> {


    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        applicationPreparedEvent.getApplicationContext().getBeanFactory().addBeanPostProcessor(new FeignClientPostProcessor());
    }
}
