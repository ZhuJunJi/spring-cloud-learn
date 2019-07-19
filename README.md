# spring-cloud-learn
## 前言
Spring Cloud 越来越流行需要学习了解
## 项目介绍
   项目使用 Nacos 作为远程配置中心实现 one jar one docker run everywhere
   实现Spring Cloud 集成 Eureka、Ribbon、Feign、Zuul、Getway、Mybatis
### 项目结构
```lua
spring-cloud-learn
├── spring-cloud-learn-common -- 框架公共模块
├── spring-cloud-learn-eureka-server -- Eureka Server 注册中心
├── spring-cloud-learn-eureka-client -- Eureka 客户端 服务消费者
├── spring-cloud-learn-getway -- Getway 服务网关
├── spring-cloud-learn-zuul -- Zuul 服务网关
├── spring-cloud-learn-ribbon -- Ribbon 负载均衡
├── spring-cloud-learn-user -- 用户模块
|    ├── spring-cloud-learn-user-common -- 用户公共模块
|    ├── spring-cloud-learn-user-mapper -- 用户Dao
|    ├── spring-cloud-learn-user-microservice-api -- 用户功能接口包
|    ├── spring-cloud-learn-user-microservice-impl --用户功能服务包
|    └── spring-cloud-learn-user-microservice-server -- 用户权限系统及SSO服务端[端口:1111]
```
#### 开发环境：
- Jdk8+
- Mysql5.7+
- Redis
- Nacos
- Sentinel
- Docker
- Docker Compose

### 开发环境搭建

环境搭建和系统部署

### 资源下载
- [Mysql主从](https://github.com/ZhuJunJi/mysql-slave "Mysql主从")
- [Redis哨兵](https://github.com/ZhuJunJi/redis-sentinel "Redis哨兵")
- [Docker](https://docs.docker.com/install/linux/docker-ce/centos/ "Docker")
- [Docker Compose](https://docs.docker.com/compose/install/ "Docker Compose")
- [Maven](http://maven.apache.org/download.cgi "Maven")
- [Nacos](https://github.com/alibaba/nacos/releases "Nacos")
- [Nacos 集群搭建](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html "Nacos 集群搭建")
- [Alibaba Sentinel](https://github.com/alibaba/Sentinel "Alibaba Sentinel")

### Nacos配置规范
用namespace来区分各个环境的配置

配置文件名称空间：           dev、test、prd

Sentinel流控策略名称空间：   sentinel-dev、sentinel-test、sentinel-prd

dataId:初始化配置文件统一为： ${spring.application.name}.yml

其他如mysql、redis：        ${spring.application.name}-mysql.yml、${spring.application.name}-redis.yml

group统一为：               ${spring.application.name}
