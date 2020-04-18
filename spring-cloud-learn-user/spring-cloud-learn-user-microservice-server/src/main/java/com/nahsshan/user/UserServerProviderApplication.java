package com.nahsshan.user;

import com.nahsshan.common.tomcat.CustomShutdown;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@MapperScan(value = "com.nahsshan.user.mapper")
@SpringBootApplication(
        // 部分通用拦截器放在common包下需要添加扫描包
        scanBasePackages={"com.nahsshan.common","com.nahsshan.user"},
        exclude = {
                DataSourceAutoConfiguration.class,
                KafkaAutoConfiguration.class
})
@EnableDiscoveryClient
@EnableTransactionManagement
public class UserServerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerProviderApplication.class, args);
    }

    @Bean
    public CustomShutdown customShutdown(){
        return new CustomShutdown();
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final CustomShutdown customShutdown){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(customShutdown);
        return tomcatServletWebServerFactory;
    }
}
