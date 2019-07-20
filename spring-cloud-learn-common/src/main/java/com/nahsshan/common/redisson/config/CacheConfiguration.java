package com.nahsshan.common.redisson.config;

import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author J.zhu
 * @date 2019/7/19
 */
@Configuration
@EnableConfigurationProperties(RedisConfig.class)
public class CacheConfiguration {

    @Autowired
    RedisConfig redisConfig;

    @Configuration
    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'=='single' or '${spring.redis.mode}'=='cluster' or '${spring.redis.mode}'=='sentinel'")
    protected class RedissonSingleClientConfiguration {

        /**
         * 单机模式 redisson 客户端
         */

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
        RedissonClient redissonSingle() {
            Config config = new Config();
            String node = redisConfig.getSingle().getAddress();
            node = node.startsWith("redis://") ? node : "redis://" + node;
            SingleServerConfig serverConfig = config.useSingleServer()
                    .setAddress(node)
                    .setTimeout(redisConfig.getPool().getConnTimeout())
                    .setConnectionPoolSize(redisConfig.getPool().getSize())
                    .setConnectionMinimumIdleSize(redisConfig.getPool().getMinIdle());
            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                serverConfig.setPassword(redisConfig.getPassword());
            }
            return Redisson.create(config);
        }


        /**
         * 集群模式的 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        RedissonClient redissonCluster() {
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
                    .setIdleConnectionTimeout(
                            redisConfig.getPool().getSoTimeout())
                    .setConnectTimeout(
                            redisConfig.getPool().getConnTimeout())
                    .setFailedAttempts(
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

        /**
         * 哨兵模式 redisson 客户端
         * @return
         */

        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        RedissonClient redissonSentinel() {
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
                    .setMasterConnectionPoolSize(redisConfig.getPool().getSize())
                    .setSlaveConnectionPoolSize(redisConfig.getPool().getSize());

            if (StringUtils.isNotBlank(redisConfig.getPassword())) {
                serverConfig.setPassword(redisConfig.getPassword());
            }

            return Redisson.create(config);
        }
    }
}
