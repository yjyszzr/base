package com.dl.base.auth.client.interceptor;

import com.alibaba.fastjson.JSON;
import com.dl.base.auth.client.config.UserAuthConfig;
import com.dl.base.auth.client.jwt.UserAuthUtil;
import com.dl.base.context.BaseContextHandler;
import com.dl.base.model.Address;
import com.dl.base.model.UserDeviceInfo;
import com.dl.base.util.jwt.IJWTInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.dl.base.constant.CommonConstants.HTTP_HEADER_ADDRESS;
import static com.dl.base.constant.CommonConstants.HTTP_HEADER_DEVICE;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

/**
 * Created by ace on 2017/9/10.
 */
@Slf4j
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader(userAuthConfig.getTokenHeader());
            if (StringUtils.isNotEmpty(token)) {
                IJWTInfo infoFromToken = userAuthUtil.getInfoFromToken(token);
                BaseContextHandler.setToken(token);
                BaseContextHandler.setUserID(infoFromToken.getUserId());
            }
        } catch (Exception e) {
            log.warn("token 解析失败", e);
        }

        String addressStr = request.getHeader(HTTP_HEADER_ADDRESS);
        if (StringUtils.isNotEmpty(addressStr)) {
            try {
                Address address = JSON.parseObject(addressStr, Address.class);
                BaseContextHandler.setAddress(address);
            } catch (Exception e) {
                log.error("解析地址信息失败，地址信息为：" + addressStr, e);
            }
        }
        String deviceStr = request.getHeader(HTTP_HEADER_DEVICE);

        if (StringUtils.isNotEmpty(deviceStr)) {
        	try {
        		UserDeviceInfo deviceInfo = JSON.parseObject(deviceStr, UserDeviceInfo.class);
        		if(deviceInfo != null) {
        			String ipAddr = this.getIpAddr(request);
        			deviceInfo.setUserIp(ipAddr);
        		}
        		BaseContextHandler.setAddress(deviceInfo);
        	} catch (Exception e) {
        		log.error("解析用户手机设备信息失败，手机设备信息为：" + deviceStr, e);
        	}
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
    
    private String getIpAddr(HttpServletRequest request) { 
		String ip = request.getHeader("X-Real-IP");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			ip = request.getHeader("X-Forwarded-For");   
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			ip = request.getHeader("Proxy-Client-IP");   
		}   
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			ip = request.getHeader("WL-Proxy-Client-IP");   
		}
		if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {   
		    ip = request.getHeader("HTTP_CLIENT_IP");   
		}  
		if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {   
		    ip = request.getHeader("HTTP_X_FORWARDED_FOR");   
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			ip = request.getRemoteAddr(); 
		}
		if(ip != null && ip.equals("127.0.0.1")){     
			//根据网卡取本机配置的IP     
			InetAddress inet=null;     
			try {     
				inet = InetAddress.getLocalHost();     
			} catch (UnknownHostException e) {     
				e.printStackTrace();     
			}     
			ip= inet.getHostAddress();     
		}  
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
		if(ip != null && ip.length() > 15){    
			if(ip.indexOf(",")>0){     
				ip = ip.substring(0,ip.indexOf(","));     
			}     
		}     
		return ip;   
	}
}
