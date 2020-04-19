package com.nahsshan.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * SSO SpringBootApplication
 * @author J.zhu
 */
@SpringBootApplication(
        scanBasePackages = {"com.nahsshan.sso","com.nahsshan.common.exception","com.nahsshan.common.db","com.nahsshan.common.exception"},
        exclude = {DataSourceAutoConfiguration.class}
)
@EnableResourceServer
public class Oauth2SsoAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SsoAuthServerApplication.class, args);
    }
}
