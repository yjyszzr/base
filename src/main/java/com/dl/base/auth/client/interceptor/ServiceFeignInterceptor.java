package com.dl.base.auth.client.interceptor;

import static com.dl.base.constant.CommonConstants.HTTP_HEADER_ADDRESS;
import static com.dl.base.constant.CommonConstants.HTTP_HEADER_DEVICE;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dl.base.auth.client.config.UserAuthConfig;
import com.dl.base.context.BaseContextHandler;
import com.dl.base.model.Address;
import com.dl.base.model.UserDeviceInfo;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @date 2017/9/15
 */
public class ServiceFeignInterceptor implements RequestInterceptor {

    @Autowired
    private UserAuthConfig userAuthConfig;


    public ServiceFeignInterceptor() {
    }


    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(userAuthConfig.getTokenHeader(), BaseContextHandler.getToken());
        Address address = BaseContextHandler.getAddress();
        if (null != address) {
            requestTemplate.header(HTTP_HEADER_ADDRESS, JSON.toJSONString(address));
        }
        UserDeviceInfo deviceInfo = BaseContextHandler.getUserDeviceInfo();
        if (null != deviceInfo) {
        	requestTemplate.header(HTTP_HEADER_DEVICE, JSON.toJSONString(deviceInfo));
        }
    }

    public void setUserAuthConfig(UserAuthConfig userAuthConfig) {
        this.userAuthConfig = userAuthConfig;
    }

}