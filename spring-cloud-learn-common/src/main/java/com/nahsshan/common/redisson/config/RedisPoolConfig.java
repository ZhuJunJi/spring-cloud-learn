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

    private int maxIdle;

    private int maxTotal;

    private int minIdle;
    /**
     * 单位毫秒
     */
    private int commandTimeout;

    /**
     * 单位毫秒
     */
    private int shutdownTimeout;
}
