package com.nahsshan.gateway;

import com.nahsshan.gateway.ribbon.NacosFinalRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author J.zhu
 * @date 2019/7/17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }

    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则改为随机
        return new NacosFinalRule();
    }
}
