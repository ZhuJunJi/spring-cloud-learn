package com.nahsshan.common.redis.config;

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
    private String master = "master";

    /**
     * 哨兵节点
     */
    private String nodes = "www.nahsshan.com:26379,www.nahsshan.com:26380,www.nahsshan.com:26381";

    /**
     * SLAVE/MASTER/MASTER_SLAVE
     */
    private String readMode = "SLAVE";

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     * Redis Slave node failing to execute commands is excluded from the internal list of available nodes
     * when the time interval from the moment of first Redis command execution failure
     * on this server reaches <code>slaveFailsInterval</code> value.
     */
    private int slaveFailsInterval;

    private int failedSlaveReconnectionInterval = 3000;

    /**
     * （主节点连接池大小）默认值：64
     */
    private int masterConnectionPoolSize = 64;

    /**
     * Redis 'master' node minimum idle connection amount for <b>each</b> slave node
     */
    private int masterConnectionMinimumIdleSize = 24;

    /**
     * （从节点连接池大小） 默认值：64
     */
    private int slaveConnectionPoolSize = 64;

    /**
     * Minimum idle connection pool size for subscription (pub/sub) channels
     */
    private int slaveConnectionMinimumIdleSize = 24;

}
