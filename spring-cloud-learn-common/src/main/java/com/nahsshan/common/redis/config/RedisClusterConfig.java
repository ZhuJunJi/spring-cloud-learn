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
public class RedisClusterConfig {

    private Integer maxRedirects;

    /**
     * 集群状态扫描间隔时间，单位是毫秒
     */
    private int scanInterval = 5000;

    /**
     * 集群节点
     */
    private String nodes;

    /**
     * 默认值： SLAVE（只在从服务节点里读取）设置读取操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里读取。
     * MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取
     */
    private String readMode;

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

    /**
     * 从第一次无法执行命令开始计时，超时后将被移除Redis服务列表
     * Redis Slave node failing to execute commands is excluded from the internal list of available nodes
     * when the time interval from the moment of first Redis command execution failure
     * on this server reaches <code>slaveFailsInterval</code> value.
     * <p>
     * Default is <code>180000</code>
     *
     */
    private int failedSlaveCheckInterval = 180000;

    /**
     * 断开后的Redis服务重试时间周期
     * Interval of Redis Slave reconnection attempt when
     * it was excluded from internal list of available servers.
     * <p>
     * On every such timeout event Redisson tries
     * to connect to disconnected Redis server.
     * <p>
     * Default is 3000
     *
     */
    private int failedSlaveReconnectionInterval = 3000;
}
