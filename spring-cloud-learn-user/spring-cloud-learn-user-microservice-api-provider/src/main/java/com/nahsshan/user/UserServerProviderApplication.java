package com.nahsshan.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan(value = "com.nahsshan.user.mapper")
@ComponentScan(basePackages = {"com.nahsshan.common.mybatis"})
public class UserServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerProviderApplication.class, args);
    }

}
