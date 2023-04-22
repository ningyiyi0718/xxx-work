# xxx-work

> Spring Cloud Alibaba version as below

|  Spring Cloud Alibaba Version  |  Spring Cloud Version  |  Spring Boot Version  |
| :----------------------------: | :--------------------: | :-------------------: |
| 2.2.1.RELEASE                  | Spring Cloud Hoxton.SR3|   2.2.5.RELEASE       |

---

> Other techs as below  

* slf4j + logback：SpringBoot默认支持slf4j，导入依赖\logback.xml
* nacos：持久化\导入依赖\bootstrap.yml\注解支持
* gateway：解决中文乱码\跨域访问问题\全局过滤器\局部过滤器(Mono)
* mysql：连接池\HikariCp
* mybatis-plus：主从多数据源\分页
* rocketmq：集群\事务消息
* open-feign：导入依赖\注解支持\相关配置\接口实现
* sentinel：gateway整合\持久化(file,nacos,redis,zk)
* sharding-jdbc：读写分离

* skywalking：待定
* xxl-job：待定