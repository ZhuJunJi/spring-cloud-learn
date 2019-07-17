package com.nahsshan.eureka;

import com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration;
import com.nahsshan.eureka.ribbon.NacosFinalRule;
import com.netflix.loadbalancer.IRule;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author J.zhu
 * @date 2019/7/10
 */
@SpringBootApplication(
        exclude = {
        DataSourceAutoConfiguration.class,
        RedissonAutoConfiguration.class,
        RedisAutoConfiguration.class,
        KafkaAutoConfiguration.class,
        NacosConfigAutoConfiguration.class
})
//@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
//@RibbonClients(defaultConfiguration = NacosRibbonClientExtendConfiguration.class)
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
