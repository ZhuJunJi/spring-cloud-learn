package com.nahsshan.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author J.zhu
 */
@Configuration
@EnableAuthorizationServer
@ConditionalOnClass({RedisConnectionFactory.class, UserDetailsService.class})
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * {@link SecurityConfiguration#authenticationManager()}
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * {@link com.nahsshan.sso.service.AuthUserDetailService}
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * {@link com.nahsshan.sso.config.SecurityConfiguration#tokenStore(RedisConnectionFactory)} ()}
     */
    @Autowired
    private TokenStore tokenStore;

    /**
     * 配置令牌端点(Token Endpoint)的安全约束；
     *
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
    }

    /**
     * 配置客户端详情服务
     * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息；
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

        // 配置两个客户端，一个用于password认证一个用于client认证
        clients.inMemory().withClient("user-controller")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret);
    }

    /**
     * 配置授权
     * 以及令牌（token）的访问端点和令牌服务(token services)，
     * token的存储方式(tokenStore)；
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                //若无，refresh_token会有UserDetailsService is required错误
                .reuseRefreshTokens(true)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);;
    }

}