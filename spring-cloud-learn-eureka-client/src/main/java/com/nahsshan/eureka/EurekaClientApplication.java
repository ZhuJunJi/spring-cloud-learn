package com.nahsshan.eureka;

import com.alibaba.cloud.nacos.ribbon.RibbonNacosAutoConfiguration;
import com.nahsshan.eureka.ribbon.NacosFinalRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author J.zhu
 * @date 2019/7/10
 */
@SpringBootApplication
//@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@RibbonClients(defaultConfiguration = RibbonNacosAutoConfiguration.class)
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则改为随机
        return new NacosFinalRule();
    }
}
