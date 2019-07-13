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

### 开发环境搭建

环境搭建和系统部署

### 资源下载

- [Mysql主从](https://github.com/ZhuJunJi/mysql-slave "Mysql主从")
- [Redis哨兵](https://github.com/ZhuJunJi/redis-sentinel "Redis哨兵")