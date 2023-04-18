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
* mybatis-plus：主从多数据源
* rocketmq：集群\事务消息