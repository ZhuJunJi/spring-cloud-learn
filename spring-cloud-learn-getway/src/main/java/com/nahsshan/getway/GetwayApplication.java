package com.nahsshan.getway;

import com.nahsshan.getway.ribbon.NacosFinalRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author J.zhu
 * @date 2019/7/17
 */
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class GetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class);
    }

    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则改为随机
        return new NacosFinalRule();
    }
}
