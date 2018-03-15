package com.dl.base.filter;

import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 服务依赖配置
 *
 * @since 2017/12/9 下午11:18
 **/
@Data
@ConfigurationProperties("eureka.client")
public class ServiceDependConfig implements InitializingBean {

    /**
     * 服务依赖配置
     */
    private Map<String, String> group;


    /**
     * 获取应用组名
     *
     * @param appName 应用名称
     * @return 组名
     */
    public String getGroupName(String appName) {
        if (group == null) {
            return null;
        }
        String groupName = group.get(appName.toUpperCase());
        if (StringUtils.isEmpty(groupName)) {
            groupName = group.get("DEFAULT");
        }
        return groupName;
    }

    @Override
    public void afterPropertiesSet() {
        if (null != group) {
            Map<String, String> map = Maps.newHashMap();
            group.forEach((key, value) -> map.put(key.toUpperCase(), value.toUpperCase()));
            group = map;
        }
    }
}
