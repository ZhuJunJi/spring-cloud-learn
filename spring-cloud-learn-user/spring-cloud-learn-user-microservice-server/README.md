## Nacos配置中心配置
```yaml
spring:
  datasource:
    master:
      jdbc-url: jdbc:mysql://192.168.137.100:3306/nahsshan
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
    # 只读账户
    slave1:
      jdbc-url: jdbc:mysql://192.168.137.100:3306/nahsshan
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
    # 只读账户
    slave2:
      jdbc-url: jdbc:mysql://192.168.137.100:3306/nahsshan
      username: root
      password: "123456"
      driver-class-name: com.mysql.cj.jdbc.Driver
  cloud:
    nacos:
      discovery:
        namespace: 76c05fec-31cb-4c9d-a97b-cbabbbb7c56b
        server-addr: 192.168.137.100:8848,192.168.137.100:8849,192.168.137.100:8850
        metadata: 
          version: 1.0.0
    sentinel:
      transport:
        dashboard: 192.168.137.1:7200
      datasource:
        ds:
          nacos:
            namespace: 76c05fec-31cb-4c9d-a97b-cbabbbb7c56b
            server-addr: 192.168.137.100:8848,192.168.137.100:8849,192.168.137.100:8850
            dataId: sentinel.json
            groupId: sentinel
            ruleType: flow
#mybatis:
#  mapper-locations: "classpath:mapper/*.xml"
# eureka:
#   client:
#     service-url:
#       # 注册到注册中心
#       defaultZone: http://192.168.137.1:8000/eureka/,http://192.168.137.1:8001/eureka/,http://192.168.137.1:8002/eureka/
#     registry-fetch-interval-seconds: 10
#   instance:
#     hostname: localhost
#     prefer-ip-address: true
#     instance-id: ${spring.cloud.client.ip-address}:${server.port}
management:
  endpoint:
    health:
      show-details: always
```
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
