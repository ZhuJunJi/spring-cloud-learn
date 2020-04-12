## Nacos配置中心配置
data-id: spring-cloud-learn-user-microservice-server-provider.yml
group: spring-cloud-learn-user-microservice-server-provider
```yaml
spring:
    nacos:
      discovery:
        namespace: 2a5d3ccd-d57a-4ef2-b702-60f7b2ba510b
        server-addr: 127.0.0.1:8848
        metadata: 
          version: 1.0.0
```
data-id: spring-cloud-learn-user-microservice-server-provider-mysql.yml
group: spring-cloud-learn-user-microservice-server-provider
```yaml
spring:
  datasource:
    master:
      jdbc-url: jdbc:mysql://127.0.0.1:3306/nahsshan?serverTimezone=Asia/Shanghai
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
    # 只读账户
    slave1:
      jdbc-url: jdbc:mysql://127.0.0.1:3306/nahsshan?serverTimezone=Asia/Shanghai
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
    # 只读账户
    slave2:
      jdbc-url: jdbc:mysql://127.0.0.1:3306/nahsshan?serverTimezone=Asia/Shanghai
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
```
data-id: spring-cloud-learn-user-microservice-server-provider-redis.yml
group: spring-cloud-learn-user-microservice-server-provider
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
            hostName: 127.0.0.1
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
