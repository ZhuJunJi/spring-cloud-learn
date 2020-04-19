package com.nahsshan.user;

import com.nahsshan.common.tomcat.CustomShutdown;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author J.zhu
 * @date 2019/7/11
 */
@SpringBootApplication(
        scanBasePackages = {"com.nahsshan.user.controller","com.nahsshan.common.redis","com.nahsshan.common.aspect"},
        exclude = {DataSourceAutoConfiguration.class}
)
public class UserServerControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerControllerApplication.class, args);
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
