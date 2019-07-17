package com.nahsshan.eureka.ribbon;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;


/**
 * @author J.zhu
 * @date 2019/7/17
 */
@Slf4j
public class NacosWeightRule extends AbstractLoadBalancerRule {
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public Server choose(Object key) {
        DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer) getLoadBalancer();
        String name = loadBalancer.getName();
        try {
            Instance instance = nacosDiscoveryProperties.namingServiceInstance()
                    .selectOneHealthyInstance(name);

            log.info("选中的instance = {}", instance);

            /*
             * instance转server的逻辑参考自：
             * org.springframework.cloud.alibaba.nacos.ribbon.NacosServerList.instancesToServerList
             */
            return new NacosServer(instance);
        } catch (NacosException e) {
            log.error("发生异常", e);
            return null;
        }
    }


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }
}
