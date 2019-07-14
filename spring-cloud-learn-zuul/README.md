# spring-cloud-learn-zuul
## nacos远程配置中心
### 启动nacos
### 创建命名空间 namespace:dev 会生成命名空间ID:63c8c314-574c-46cf-b4fb-50c9e304b2d3
### 在 bootstrap.yml 中配置 nacos namespace 为命名空间ID
### nacos新建配置 Data ID:zuul-application.yml Group:spring-cloud-learn-zuul 配置格式:YAML
### 配置内容
```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://192.168.137.1:8000/eureka/,http://192.168.137.1:8001/eureka/,http://192.168.137.1:8002/eureka/
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: ${spring.cloud.client.ip-address}:${server.port}
#配置zuul路由规则 指定spring-cloud-api-client      http://localhost:6060/api-provider/* => http://localhost:802
zuul:
  routes:
    #/api-a/ 开头匹配到service-producer
    api-a:
      path: /api-a/**
      serviceId: spring-cloud-learn-eureka-client
    #/api-b/ 开头匹配到service-producer
    api-b:
      path: /api-b/**
      serviceId: service-producer
    #匹配/github/直接重定向到https://github.com/
    github:
      path: /github/**
      url: https://github.com/ZhuJunJi/spring-cloud-learn
#忽略指定服务
#zuul.ignored-services= spring-cloud-api-client
```
### 修改bootstrap.yml 中的nacos namespace data-id group
