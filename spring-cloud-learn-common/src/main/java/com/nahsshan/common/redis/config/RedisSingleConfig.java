package com.nahsshan.common.redis.config;

import lombok.Data;
import lombok.ToString;

/**
 * @author J.zhu
 * @date 2019/7/19
 */
@Data
@ToString
public class RedisSingleConfig {
    /**
     * hostName
     */
    private String hostName = "127.0.0.1";
    /**
     * port
     */
    private Integer port = 6379;
}
