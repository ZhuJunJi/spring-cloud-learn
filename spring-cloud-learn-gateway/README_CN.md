# spring-cloud-learn-getway
## 网关限流
[Sentinel 支持对 Spring Cloud Gateway、Zuul 等主流的 API Gateway 进行限流。](https://github.com/alibaba/Sentinel/wiki/%E7%BD%91%E5%85%B3%E9%99%90%E6%B5%81#spring-cloud-gateway "Sentinel")
## Nacos配置中心配置
data-id: spring-cloud-learn-gateway.yml
group: spring-cloud-learn-gateway
```yaml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true
    nacos:
      discovery:
        namespace: 2a5d3ccd-d57a-4ef2-b702-60f7b2ba510b
        server-addr: 127.0.0.1:8848
    sentinel:
      transport:
        dashboard: 127.0.0.1:7200
      datasource:
        ds:
          nacos:
            namespace: 2a5d3ccd-d57a-4ef2-b702-60f7b2ba510b
            server-addr: 127.0.0.1:8848
            dataId: gateway-sentinel.json
            groupId: ${spring.application.name}
            ruleType: flow
```