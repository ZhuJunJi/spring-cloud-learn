package com.nahsshan.common.redisson.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author J.zhu
 * @date 2019/7/19
 */
@Configuration
@EnableConfigurationProperties(RedisConfig.class)
public class CacheConfiguration {

    @Autowired
    private RedisConfig redisConfig;

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'.equals('single')")
    protected class RedisSingleClientConfiguration {

        @Bean
        public RedisConnectionFactory redisConnectionFactory(LettucePoolingClientConfiguration lettucePoolingClientConfiguration) {
            RedisSingleConfig redisSingleConfig = redisConfig.getSingle();
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setDatabase(redisConfig.getDatabase());
            redisStandaloneConfiguration.setHostName(redisSingleConfig.getHostName());
            redisStandaloneConfiguration.setPort(redisSingleConfig.getPort());
            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                redisStandaloneConfiguration.setPassword(redisConfig.getPassword());
            }
            return new LettuceConnectionFactory(redisStandaloneConfiguration,lettucePoolingClientConfiguration);
        }

        /**
         * 单机模式 redisson 客户端
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
        public RedissonClient redissonClient() {
            Config config = new Config();
            String address = "redis://" + redisConfig.getSingle().getHostName() + ":" + redisConfig.getSingle().getPort();
            SingleServerConfig serverConfig = config.useSingleServer()
                    .setAddress(address)
                    .setTimeout(redisConfig.getTimeout())
                    .setDatabase(redisConfig.getDatabase());
            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                serverConfig.setPassword(redisConfig.getPassword());
            }
            return Redisson.create(config);
        }
    }

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'.equals('cluster')")
    protected class RedisClusterClientConfiguration {

        /**
         * 集群模式的 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        public RedissonClient redissonClient() {
            System.out.println("cluster redisConfig:" + redisConfig.getCluster());

            Config config = new Config();
            String[] nodes = redisConfig.getCluster().getNodes().split(",");
            List<String> newNodes = new ArrayList(nodes.length);
            Arrays.stream(nodes).forEach((index) -> newNodes.add(
                    index.startsWith("redis://") ? index : "redis://" + index));

            ClusterServersConfig serverConfig = config.useClusterServers()
                    .addNodeAddress(newNodes.toArray(new String[0]))
                    .setScanInterval(
                            redisConfig.getCluster().getScanInterval())
                    .setFailedSlaveCheckInterval(
                            redisConfig.getCluster().getFailedAttempts())
                    .setRetryAttempts(
                            redisConfig.getCluster().getRetryAttempts())
                    .setRetryInterval(
                            redisConfig.getCluster().getRetryInterval())
                    .setMasterConnectionPoolSize(redisConfig.getCluster()
                            .getMasterConnectionPoolSize())
                    .setSlaveConnectionPoolSize(redisConfig.getCluster()
                            .getSlaveConnectionPoolSize())
                    .setTimeout(redisConfig.getTimeout());
            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                serverConfig.setPassword(redisConfig.getPassword());
            }
            return Redisson.create(config);
        }
    }

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'.equals('sentinel')")
    protected class RedisSentinelClientConfiguration {

        @Bean
        public RedisConnectionFactory redisConnectionFactory(LettucePoolingClientConfiguration lettucePoolingClientConfiguration) {
            RedisSentinelConfig redisSentinelConfig = redisConfig.getSentinel();

            RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();

            redisSentinelConfiguration.setDatabase(redisConfig.getDatabase());

            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                redisSentinelConfiguration.setPassword(redisConfig.getPassword());
            }

            return new LettuceConnectionFactory(redisSentinelConfiguration, lettucePoolingClientConfiguration);
        }

        /**
         * 哨兵模式 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        public RedissonClient redissonClient() {
            System.out.println("sentinel redisConfig:" + redisConfig.getSentinel());
            Config config = new Config();
            String[] nodes = redisConfig.getSentinel().getNodes().split(",");
            List<String> newNodes = new ArrayList(nodes.length);
            Arrays.stream(nodes).forEach((index) -> newNodes.add(
                    index.startsWith("redis://") ? index : "redis://" + index));

            SentinelServersConfig serverConfig = config.useSentinelServers()

                    .addSentinelAddress(newNodes.toArray(new String[0]))
                    .setMasterName(redisConfig.getSentinel().getMaster())
                    .setReadMode(ReadMode.SLAVE)
                    .setFailedSlaveCheckInterval(redisConfig.getSentinel().getFailMax())
                    .setTimeout(redisConfig.getTimeout())
                    .setMasterConnectionPoolSize(8)
                    .setMasterConnectionMinimumIdleSize(0)
                    .setSlaveConnectionPoolSize(8)
                    .setSlaveConnectionMinimumIdleSize(0);

            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                serverConfig.setPassword(redisConfig.getPassword());
            }

            return Redisson.create(config);
        }
    }

    @Bean
    public LettucePoolingClientConfiguration lettucePoolingClientConfiguration() {
        RedisPoolConfig pool = redisConfig.getPool();

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxIdle(pool.getMaxIdle());
        genericObjectPoolConfig.setMaxTotal(pool.getMaxTotal());
        genericObjectPoolConfig.setMinIdle(pool.getMinIdle());

        return LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(pool.getCommandTimeout()))
                .shutdownTimeout(Duration.ofMillis(pool.getShutdownTimeout()))
                .poolConfig(genericObjectPoolConfig)
                .build();
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfBaseType(Serializable.class).build());
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
