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
    private String hostName = "www.nahsshan.com";
    /**
     * port
     */
    private Integer port = 6379;
}
