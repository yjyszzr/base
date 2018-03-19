package com.dl.base.filter;

import com.netflix.loadbalancer.ZoneAffinityServerListFilter;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @since 2017/12/8 下午3:23
 **/
@Slf4j
public class ServerSelectedFilter<T extends DiscoveryEnabledServer> extends ZoneAffinityServerListFilter<T> {

    @Autowired
    private ServiceDependConfig serviceDependConfig;


    @Override
    public List<T> getFilteredListOfServers(List<T> servers) {
        log.debug("可用的服务为:{}", servers);
        String appName = getServiceName(servers);
        if (StringUtils.isNotEmpty(appName)) {
            String groupName = serviceDependConfig.getGroupName(appName);
            if (StringUtils.isNotEmpty(groupName)) {
                List<T> list = servers.stream()
                        .filter(server -> Objects.equals(server.getInstanceInfo().getAppGroupName(), groupName))
                        .collect(Collectors.toList());
                log.debug("应用 {} 依赖的组为 {}，可用的服务列表为：{}", appName, groupName, list);
                return super.getFilteredListOfServers(list);

            }
        }
        return super.getFilteredListOfServers(servers);
    }

    /**
     * 获取服务名称
     *
     * @param serverList 服务列表
     * @return 服务名称
     */
    private String getServiceName(List<T> serverList) {
        if (CollectionUtils.isNotEmpty(serverList)) {
            return serverList.get(0).getInstanceInfo().getAppName().toUpperCase();
        } else {
            return null;
        }

    }

}
