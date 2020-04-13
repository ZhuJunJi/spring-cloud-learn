package com.nahsshan.common.redisson.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author J.zhu
 * @date 2019/7/19
 */
@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
@Data
@ToString
public class RedisConfig {

    /**
     * database
     */
    private int database = 0;

    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout = 3000;

    /**
     * Timeout during connecting to any Redis server.
     * Value in milliseconds.
     *
     */
    private int connectTimeout = 10000;

    /**
     * If pooled connection not used for a <code>timeout</code> time
     * and current connections amount bigger than minimum idle connections pool size,
     * then it will closed and removed from pool.
     * Value in milliseconds.
     *
     */
    private int idleConnectionTimeout = 10000;

    /**
     * Password for Redis authentication. Should be null if not needed
     * Default is <code>null</code>
     */
    private String password;

    /**
     * redis 模式选择  single/cluster/sentinel
     */
    private String mode = "single";

    /**
     * （命令失败重试次数） 默认值：3
     */
    private int retryAttempts = 3;

    /**
     *命令重试发送时间间隔，单位：毫秒 默认值：1500
     */
    private int retryInterval = 1500;

    private int failedSlaveReconnectionInterval = 3000;

    /**
     * 池配置
     */
    private RedisPoolConfig pool;

    /**
     * 单机信息配置
     */
    private RedisSingleConfig single;

    /**
     * 集群 信息配置
     */
    private RedisClusterConfig cluster;

    /**
     * 哨兵配置
     */
    private RedisSentinelConfig sentinel;
}
