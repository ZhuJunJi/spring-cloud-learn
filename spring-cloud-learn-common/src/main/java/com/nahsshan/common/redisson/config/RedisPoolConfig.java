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

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int connTimeout;

    private int soTimeout;

    /**
     * 池大小
     */
    private  int size;
}
