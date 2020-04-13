package com.nahsshan.common.redisson.config;

import lombok.Data;
import lombok.ToString;

/**
 * Description redis 池配置
 * @author J.zhu
 * @date 2019/7/19
 */
@Data
@ToString
public class RedisPoolConfig {

    private int maxIdle = 8;

    private int maxTotal = 8;

    private int minIdle = 0;

    private long maxWaitMillis = -1L;
    /**
     * 单位毫秒
     */
    private long commandTimeout = 60000L;

    /**
     * 单位毫秒
     */
    private long shutdownTimeout = 100L;
}
