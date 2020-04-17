package com.nahsshan.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Set;

/**
 * @Description gateway限流规则转换器
 * @author J.zhu
 * @date 2019/7/17
 */
public class GatewayFlowRuleConvert implements Converter<String,Set<GatewayFlowRule>> {
    @Override
    public Set<GatewayFlowRule> convert(String source) {
        return JSON.parseObject(source, new TypeReference<Set<GatewayFlowRule>>() {});
    }
}
