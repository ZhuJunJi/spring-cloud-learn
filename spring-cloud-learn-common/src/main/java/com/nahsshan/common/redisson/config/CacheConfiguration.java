package com.nahsshan.common.redisson.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.split;

/**
 * @author J.zhu
 * @date 2019/7/19
 */
@Slf4j
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
        public RedisConfiguration redisConfiguration() {
            RedisSingleConfig redisSingleConfig = redisConfig.getSingle();
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setDatabase(redisConfig.getDatabase());
            redisStandaloneConfiguration.setHostName(redisSingleConfig.getHostName());
            redisStandaloneConfiguration.setPort(redisSingleConfig.getPort());
            redisStandaloneConfiguration.setPassword(redisConfig.getPassword());

            log.info("初始化 RedisConfiguration 单机模式");
            return redisStandaloneConfiguration;
        }

        /**
         * 单机模式 redisson 客户端
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
        public RedissonClient redissonClient() {
            Config config = new Config();
            String address = "redis://" + redisConfig.getSingle().getHostName() + ":" + redisConfig.getSingle().getPort();
            config.useSingleServer()
                    .setAddress(address)
                    .setTimeout(redisConfig.getTimeout())
                    .setConnectTimeout(redisConfig.getConnectTimeout())
                    .setIdleConnectionTimeout(redisConfig.getIdleConnectionTimeout())
                    .setRetryAttempts(redisConfig.getRetryAttempts())
                    .setRetryInterval(redisConfig.getRetryInterval())
                    .setPassword(redisConfig.getPassword())
                    .setDatabase(redisConfig.getDatabase());

            RedissonClient redissonClient = Redisson.create(config);
            log.info("Redis 单机启动 初始化 RedissonClient 成功！config:{}",redisConfig);
            return redissonClient;
        }
    }

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'.equals('cluster')")
    protected class RedisClusterClientConfiguration {

        @Bean
        public RedisConfiguration redisConfiguration() {
            RedisClusterConfig clusterConfig = redisConfig.getCluster();

            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

            if(clusterConfig.getMaxRedirects() != null){
                redisClusterConfiguration.setMaxRedirects(clusterConfig.getMaxRedirects());
            }
            if(redisConfig.getPassword() != null){
                redisClusterConfiguration.setPassword(redisConfig.getPassword());
            }

            List<RedisNode> clusterNodes = StringUtils
                    .commaDelimitedListToSet(clusterConfig.getNodes())
                    .stream()
                    .map(hostAndPort->readHostAndPortFromString(hostAndPort))
                    .collect(Collectors.toList());

            redisClusterConfiguration.setClusterNodes(clusterNodes);

            return redisClusterConfiguration;
        }

        /**
         * 集群模式的 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        public RedissonClient redissonClient() {
            RedisClusterConfig clusterConfig = redisConfig.getCluster();

            Config config = new Config();
            String[] nodes = redisConfig.getCluster().getNodes().split(",");
            List<String> newNodes = new ArrayList(nodes.length);
            Arrays.stream(nodes).forEach((index) -> newNodes.add(
                    index.startsWith("redis://") ? index : "redis://" + index));

            config.useClusterServers()
                    .addNodeAddress(newNodes.toArray(new String[0]))
                    .setTimeout(redisConfig.getTimeout())
                    .setConnectTimeout(redisConfig.getConnectTimeout())
                    .setIdleConnectionTimeout(redisConfig.getIdleConnectionTimeout())
                    .setRetryAttempts(redisConfig.getRetryAttempts())
                    .setRetryInterval(redisConfig.getRetryInterval())
                    .setScanInterval(clusterConfig.getScanInterval())
                    .setPassword(redisConfig.getPassword())
                    .setFailedSlaveCheckInterval(clusterConfig.getFailedSlaveCheckInterval())
                    .setFailedSlaveReconnectionInterval(clusterConfig.getFailedSlaveReconnectionInterval())
                    .setMasterConnectionPoolSize(clusterConfig.getMasterConnectionPoolSize())
                    .setMasterConnectionMinimumIdleSize(clusterConfig.getMasterConnectionMinimumIdleSize())
                    .setSlaveConnectionPoolSize(clusterConfig.getSlaveConnectionPoolSize())
                    .setSlaveConnectionMinimumIdleSize(clusterConfig.getSlaveConnectionMinimumIdleSize());

            RedissonClient redissonClient = Redisson.create(config);
            log.info("Redis 集群启动 初始化 RedissonClient 成功！config:{}",redisConfig);
            return redissonClient;
        }
    }

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'.equals('sentinel')")
    protected class RedisSentinelClientConfiguration {

        @Bean
        public RedisConfiguration redisConfiguration() {
            RedisSentinelConfig sentinelConfig = redisConfig.getSentinel();

            RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();

            redisSentinelConfiguration.setDatabase(redisConfig.getDatabase());
            redisSentinelConfiguration.setMaster(sentinelConfig.getMaster());

            List<RedisNode> sentinels = StringUtils
                    .commaDelimitedListToSet(sentinelConfig.getNodes())
                    .stream()
                    .map(hostAndPort->readHostAndPortFromString(hostAndPort))
                    .collect(Collectors.toList());

            redisSentinelConfiguration.setSentinels(sentinels);

            redisSentinelConfiguration.setPassword(redisConfig.getPassword());

            return redisSentinelConfiguration;
        }

        /**
         * 哨兵模式 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        public RedissonClient redissonClient() {

            RedisSentinelConfig sentinelConfig = redisConfig.getSentinel();

            Config config = new Config();
            String[] nodes = redisConfig.getSentinel().getNodes().split(",");
            List<String> newNodes = new ArrayList(nodes.length);
            Arrays.stream(nodes).forEach((index) -> newNodes.add(
                    index.startsWith("redis://") ? index : "redis://" + index));

            config.useSentinelServers()
                    .addSentinelAddress(newNodes.toArray(new String[0]))
                    .setTimeout(redisConfig.getTimeout())
                    .setConnectTimeout(redisConfig.getConnectTimeout())
                    .setIdleConnectionTimeout(redisConfig.getIdleConnectionTimeout())
                    .setRetryAttempts(redisConfig.getRetryAttempts())
                    .setRetryInterval(redisConfig.getRetryInterval())
                    .setPassword(redisConfig.getPassword())
                    .setMasterName(sentinelConfig.getMaster())
                    .setReadMode(ReadMode.valueOf(sentinelConfig.getReadMode()))
                    .setFailedSlaveCheckInterval(sentinelConfig.getSlaveFailsInterval())
                    .setFailedSlaveReconnectionInterval(sentinelConfig.getFailedSlaveReconnectionInterval())
                    .setMasterConnectionPoolSize(sentinelConfig.getMasterConnectionPoolSize())
                    .setMasterConnectionMinimumIdleSize(sentinelConfig.getMasterConnectionMinimumIdleSize())
                    .setSlaveConnectionPoolSize(sentinelConfig.getSlaveConnectionPoolSize())
                    .setSlaveConnectionMinimumIdleSize(sentinelConfig.getSlaveConnectionMinimumIdleSize());
            RedissonClient redissonClient = Redisson.create(config);
            log.info("Redis 哨兵模式启动 初始化 RedissonClient 成功！config:{}",redisConfig);
            return redissonClient;
        }
    }

    /**
     * Redis 连接池
     * @return
     */
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration() {
        RedisPoolConfig pool = redisConfig.getPool();

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxIdle(pool.getMaxIdle());
        genericObjectPoolConfig.setMaxTotal(pool.getMaxTotal());
        genericObjectPoolConfig.setMinIdle(pool.getMinIdle());
        genericObjectPoolConfig.setMaxWaitMillis(pool.getMaxWaitMillis());

        LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration
                .builder()
                .commandTimeout(Duration.ofMillis(pool.getCommandTimeout()))
                .shutdownTimeout(Duration.ofMillis(pool.getShutdownTimeout()))
                .poolConfig(genericObjectPoolConfig)
                .build();
        log.info("初始化 LettuceClientConfiguration 成功！");
        return lettuceClientConfiguration;
    }

    /**
     * Redis 连接工厂
     * @param redisConfiguration
     * @param lettuceClientConfiguration
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(@Qualifier("redisConfiguration") RedisConfiguration redisConfiguration, LettuceClientConfiguration lettuceClientConfiguration){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration, lettuceClientConfiguration);
        log.info("初始化 RedisConnectionFactory 成功!");
        return lettuceConnectionFactory;
    }


    /**
     * RedisTemplate
     * @param redisConnectionFactory
     * @return
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
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

        log.info("初始化 RedisTemplate 成功！");
        return redisTemplate;
    }

    private static RedisNode readHostAndPortFromString(String hostAndPort) {

        String[] args = split(hostAndPort, ":");

        Assert.notNull(args, "HostAndPort need to be seperated by  ':'.");
        Assert.isTrue(args.length == 2, "Host and Port String needs to specified as host:port");
        return new RedisNode(args[0], Integer.valueOf(args[1]).intValue());
    }
}
