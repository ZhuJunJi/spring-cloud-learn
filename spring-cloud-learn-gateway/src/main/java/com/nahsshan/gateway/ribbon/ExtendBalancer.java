package com.nahsshan.gateway.ribbon;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;

import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/17
 */
public class ExtendBalancer extends Balancer {
    /**
     * 根据权重，随机选择实例
     *
     * @param instances 实例列表
     * @return 选择的实例
     */
    public static Instance getHostByRandomWeight2(List<Instance> instances) {
        return getHostByRandomWeight(instances);
    }


}
