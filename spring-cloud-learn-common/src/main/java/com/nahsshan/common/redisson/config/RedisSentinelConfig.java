package com.nahsshan.common.redisson.config;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author J.zhu
 * @date 2019/7/19
 */
@Data
@ToString
public class RedisSentinelConfig {
    /**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵节点
     */
    private String nodes;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     *
     */
    private int failMax;
}
