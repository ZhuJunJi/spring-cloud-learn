## Nacos配置中心配置
data-id: user-microservice-server-provider.yml
group: user-microservice-server-provider
```yaml
spring:
    nacos:
      discovery:
        namespace: nahsshan
        server-addr: www.nahsshan.com:8848
        metadata: 
          version: 1.0.0
```
data-id: user-microservice-server-provider-mysql.yml
group: user-microservice-server-provider
```yaml
spring:
  datasource:
    master:
      jdbc-url: jdbc:mysql://www.nahsshan.com:3306/nahsshan?serverTimezone=Asia/Shanghai
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
    # 只读账户
    slave1:
      jdbc-url: jdbc:mysql://www.nahsshan.com:3306/nahsshan?serverTimezone=Asia/Shanghai
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
    # 只读账户
    slave2:
      jdbc-url: jdbc:mysql://www.nahsshan.com:3306/nahsshan?serverTimezone=Asia/Shanghai
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
```
data-id: user-microservice-server-provider-redis.yml
group: user-microservice-server-provider
```yaml
spring:
    redis:
        # redis 模式选择 single（单机）/cluster（集群）/sentinel（哨兵）
        mode: single
        password: 123456
        timeout: 3000
        pool:
            maxIdle: 8
            maxTotal: 8
            minIdle: 0
            commandTimeout: 60000
            shutdownTimeout: 100
        single:
            hostName: www.nahsshan.com
            port: 6379
```
data-id: sentinel.json
group: sentinel
## sentinel 限流策略配置
```json
[
    {
        "resource": "/user/get",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```
