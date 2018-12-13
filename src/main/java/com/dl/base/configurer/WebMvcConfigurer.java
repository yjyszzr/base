package com.dl.base.configurer;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.base.Joiner;
import com.dl.base.aspect.AccessLogAspect;
import com.dl.base.auth.client.interceptor.UserAuthRestInterceptor;
import com.dl.base.auth.client.jwt.UserAuthUtil;
import com.dl.base.exception.ServiceException;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.base.util.JSONHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring MVC 配置
 */
@Configuration
@Import(AccessLogAspect.class)
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Bean
    public FastJsonConfig getFastJsonConfig() {
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                //保留空的字段
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect,
                // all -> String
                SerializerFeature.WriteNonStringValueAsString,
                SerializerFeature.WriteNullListAsEmpty,
                //String null -> ""
                SerializerFeature.WriteNullStringAsEmpty);
        return config;
    }

    /**
     * 消息转换器配置
     *
     * @param converters 转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFastJsonConfig(getFastJsonConfig());
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


    /**
     * 统一异常处理
     *
     * @param exceptionResolvers 移除处理拦截链
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add((request, response, handler, e) -> {
            BaseResult result;
            //业务失败的异常，如“账号或密码错误”
            if (e instanceof ServiceException) {
                result = ResultGenerator.genResult(((ServiceException) e).getCode(), ((ServiceException) e).getMsg());
            } else if (e instanceof NoHandlerFoundException) {
                result = ResultGenerator.genNotFoundResult("接口 [" + request.getRequestURI() + "] 不存在");
            } else if (e instanceof ServletException) {
                result = ResultGenerator.genFailResult(e.getMessage());
            } else if (e instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
                List<String> allErrors = methodArgumentNotValidException.getBindingResult()
                        .getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                        .collect(Collectors.toList());
                result = ResultGenerator.genBadRequestResult(Joiner.on(';').join(allErrors));
            } else {
                result = ResultGenerator.genServerErrorResult("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                } else {
                    message = e.getMessage();
                }
                logger.error(message, e);
            }
            responseResult(response, result);
            return new ModelAndView();
        });
    }


    /**
     * 拦截器配置
     *
     * @param registry 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor());
    }

    private void responseResult(HttpServletResponse response, BaseResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSONHelper.bean2json(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Bean
    public UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    @Bean
    public UserAuthUtil getUserAuthUtil() {
        return new UserAuthUtil();
    }
}
