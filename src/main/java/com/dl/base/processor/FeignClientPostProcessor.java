package com.dl.base.processor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;

/**
 * 拦截feignClient方法
 *
 * @since 15/01/2018 2:34 PM
 */
@Slf4j
public class FeignClientPostProcessor implements BeanPostProcessor {

    /**
     * 代理缓存
     */
    private Map<Class, Object> cache = Maps.newHashMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Field[] allFields = FieldUtils.getAllFields(clazz);
        for (Field field : allFields) {
            Type type = field.getGenericType();
            if (type instanceof Class) {
                Class fieldClazz = (Class) type;
                if (fieldClazz.getAnnotation(FeignClient.class) != null) {
                    field.setAccessible(true);
                    try {
                        Object feignClient = field.get(bean);
                        field.set(bean, createProxy(feignClient, fieldClazz));
                    } catch (Exception e) {
                        log.error("代理feign失败", e);
                    }
                }
            }
        }
        return bean;
    }

    /**
     * 创建代理
     *
     * @param feignClient 被代理实例
     * @param clazz       接口类
     * @return
     */
    public Object createProxy(Object feignClient, Class clazz) {
        if (cache.containsKey(clazz)) {
            return cache.get(clazz);
        }
        Object target = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            try {
                Object result = method.invoke(feignClient, args);
                log.info(">>>>>>>> call feign {}.{}, the param is -> {} , the result is -> {}", method.getDeclaringClass().getName(), method.getName(), ArrayUtils.isNotEmpty(args) ? Lists.newArrayList(args) : Lists.newArrayList(), result);
                return result;
            } catch (Throwable e) {
                log.warn(">>>>>>>> call feign {}.{} failed, the param is -> {} ", method.getDeclaringClass().getName(), method.getName(), ArrayUtils.isNotEmpty(args) ? Lists.newArrayList(args) : Lists.newArrayList());
                while (true) {
                    while (!(e instanceof InvocationTargetException)) {
                        if (!(e instanceof UndeclaredThrowableException)) {
                            throw e;
                        }

                        e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
                    }
                    e = ((InvocationTargetException) e).getTargetException();
                }
            }
        });
        cache.put(clazz, target);
        return target;
    }

}


