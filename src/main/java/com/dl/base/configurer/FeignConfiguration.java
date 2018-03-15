package com.dl.base.configurer;


import com.dl.base.auth.client.config.UserAuthConfig;
import com.dl.base.auth.client.interceptor.ServiceFeignInterceptor;
import com.dl.base.filter.ServerSelectedFilter;
import com.dl.base.filter.ServiceDependConfig;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfiguration {

    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    ServiceFeignInterceptor getServiceFeignInterceptor() {
        return new ServiceFeignInterceptor();
    }

    @Bean
    UserAuthConfig getUserAuthConfig() {
        return new UserAuthConfig();
    }


    @Bean
    ServiceDependConfig getServiceDependConfig() {
        return new ServiceDependConfig();
    }

    @Bean
    ServerSelectedFilter getServerSelectedFilter() {
        return new ServerSelectedFilter();
    }
}
