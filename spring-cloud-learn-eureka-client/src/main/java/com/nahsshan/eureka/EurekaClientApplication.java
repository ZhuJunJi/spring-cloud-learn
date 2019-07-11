package com.nahsshan.eureka;

import com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration;
import com.nahsshan.user.service.UserService;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @author J.zhu
 * @date 2019/7/10
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        RedissonAutoConfiguration.class,
        RedisAutoConfiguration.class,
        KafkaAutoConfiguration.class,
        NacosConfigAutoConfiguration.class
})
@EnableCircuitBreaker
@EnableFeignClients(clients = UserService.class)
@EnableDiscoveryClient
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
