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

    private int database = 0;

    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout = 3000;

    private String password = "123456";

    private String mode;

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
