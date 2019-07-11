package com.nahsshan.user;

import com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@MapperScan(value = "com.nahsshan.user.mapper")
@SpringBootApplication(exclude = {
        RedissonAutoConfiguration.class,
        RedisAutoConfiguration.class,
        KafkaAutoConfiguration.class,
        NacosConfigAutoConfiguration.class
})
@EnableHystrix
@EnableDiscoveryClient
public class UserServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerProviderApplication.class, args);
    }
}