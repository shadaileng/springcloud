# <center>Spring Cloud</center>

[TOC]

# 一、引言

## 1. 五大难点

- `Eureka`服务注册与发现
- `Ribbon`和`Feign`负载均衡
- `Hystrix`断路器
- `zuul`路由网关
- `SpringCloud Config`分布式配置中心

## 关于微服务的常见问题

1. 什么是微服务
2. 微服务之间是如何独立通讯的
3. `SpringCloud`和`Dubbo`有哪些区别
    - 通信机制: `Dubbo`是基于`RPC`远程过程调用,`SpringCloud`是基于`HTTP`的`RESTful API`
---
4. 对`SpringBoot`和`SpringCloud`的理解

- `Spring Boot`是开发独立的微服务,`Spring Cloud`是多个微服务的集合体
- `Spring Cloud`依赖于`Spring Boot`,`Spring Boot`不依赖`Spring Cloud`
---
5. 什么是服务融断和服务降级
6. 微服务优缺点有哪些?项目开发中的坑
7. 微服务技术栈有哪些
8. `Eureka`和`zookeeper`都可以提供服务注册与发现功能,两者的区别是什么

# 二、微服务概述

> 就目前而言,对于微服务业界并没有一个统一的标准定义

> 一般来说,微服务是一种架构模式或是一种架构风格.==它提倡将单一应用程序划分为一组小的服务==,每个服务运行在其==独立的进程==中,服务之间互相协调,互相配合,为用户提供最终的价值.服务之间采用轻量级的通信机制互相沟通(通常是基于`HTTP`的`Restful API`).每个服务围绕具体的业务构建,并且能够独立部署到生产环境.另外,应该尽量避免统一的、集中式的服务管理机制,对具体的一个服务而言,应该根据业务上下文,选择合适的语言和工具对其构建,可以使用一个轻量级的集中式管理来协调这些服务.可以使用不同的语言来编写服务,也可以使用不同的数据存储.

---

> 微服务的核心是将传统的一站式应用,根据业务拆分成一个个的服务,彻底去耦合,每个微服务提供单个业务功能服务,一个服务做一件事.

> 从技术角度来看就是一种小而独立的处理过程,能够自行单独启动和销毁,拥有自己独立的数据库.

## 1 微服务与微服务架构

- 微服务是具体解决对应问题和提供服务的的一个服务应用

- 微服务架构是一种架构模式,将单一应用拆分为一组微小服务,服务之间相互协调,相互配合.

## 2 微服务优缺点

优点:

- 每个服务足够内聚,足够小代码容易理解业务功能和需求
- 开发简单,提高开发效率,一个服务只专注一个功能
- 服务解耦
- 可以使用不同语言开发
- 易于与第三方集成,允许自动部署
- 微服务只是业务代码,与前端界面无关
- 每个微服务都有自己的存储能力,可以有自己的数据库,也可以有统一的数据库

缺点:

- 处理分布式系统的复杂性
- 运维难度增加
- 系部署依赖
- 服务通信成本
- 数据一致性
- 系统集成测试
- 性能监控

## 3 微服务技术栈

微服务技术栈是多种技术的集合

| 微服务条目       | 技术                                      | 备注 |
| ---------------- | ----------------------------------------- | ---- |
| 服务开发         | `springboot`,`Spring`,`SpringMVC`         |      |
| 服务配置与管理   | `Netflix`公司的`Archaius`,阿里的`Diamond` |      |
| 服务注册与发现   | `Rureka`,`Consul`,`Zookeeper`             |      |
| 服务调用         | `Rest`,`RPC`, `gRPC`                      |      |
| 服务融断器       | `Hystrix`,`Envoy`                         |      |
| 负载均衡         | `Ribbon`,`Nginx`                          |      |
| 服务接口调用     | `Feign`                                   |      |
| 消息队列         | `Kafka`,`RabbitMQ`,`ActiveMQ`             |      |
| 服务配置中心管理 | `SpringCloudConfig`,`Chef`                |      |
| 服务路由         | `Zuul`                                    |      |
| 服务监控         | `Zabbix`, `Nagios`, `Metrics`,`Spectator` |      |
| 全链路追踪       | `Zipkin`,`Brave`,`Dapper`                 |      |
| 服务部署         | `Docker`,`OpenStack`,`Kubernetes`         |      |
| 数据流操作开发包 | `SpringCloudStream`                       |      |
| 事件消息总线     | `SpringCloudBus`                          |      |

## 4 为什么选择Spring Cloud

1. 依据
    - 整体解决方案和框架熟练度
    - 社区热度
    - 可维护性
    - 学习曲线
2. 当前微服务框架
    - 阿里的`Dubbo`和`HSF`
    - 京东的`JSF`
    - 新浪微博的`Motan`
    - 当当网的`DubboX`
3. 微服务框架对比

-|`NetFlix`/`Spring Cloud`|`Motan`|`gRPC`|`Thrif`|`Dubbo`/`DubboX`
---|---|---|---|---|---
功能|完整的微服务框架|`RPC`框架,整合`Zookeeper`或`Consul`,实现集群环境的基本服务注册和发现|`RPC`框架|`RPC`框架|`RPC`框架
支持`Rest`|是,`Ribbon`支持多种可插拔的序列化选择|否|否|否|否
支持`RPC`|否|是|是|是|是
支持多语言|是(`Rest`)|否|是|是|否
服务注册/发现|是(`Eureka`),`Eureka`服务注册表,`Karyon`服务框架支持服务自注册和健康检查|是(`Zookeeper`或`Consul`)|否|否|是
负载均衡|是(服务端`zuul`+客户端`Ribbon`)|是(客户端)|否|否|是(客户端)
配置服务|`Netflix`公司的`Archaius`,`SpringCloudConfig`|是(`Zookeeper`)|否|否|否
服务调用链监控|是(`zuul`)|否|否|否|否
高可用|是(服务端`Hystrix`+客户端`Ribbon`)|是(客户端)|否|否|是(客户端)
典型应用案例|`Netflix`|`Sina`|`Google`|`Facebook`|
社区活跃度|高|一般|高|一般|不再维护
学习难度|中等|低|高|高|低
文档丰富度|高|一般|一般|高
其他|`SpringCloudBus`提供监控点|支降级|`Netflix`内部在开发集成`gRPC`|`IDL`定义|实践公司较多

# 三、Spring Cloud 概述

> `Spring Cloud`是基于`Spring Boot`提供的一整套微服务解决方案,包括服务注册与发现,配置中心,全路链监控,服务网关,负载均衡,融断器等组件,除了基于`Netflix`的开源组件做高度抽象封装之外,还有一些选型中立的开源组件.

> `Spring Cloud`利用`Spring Boot`的开发便利性,巧妙地简化了分布式系统基础设施的开发,`Spring Cloud`为开发人员提供了快速构建分布式系统的一些工具,包括配置管理,服务发现,断路器,路由,微代理,事件总线,全局锁,决策竞选分布式会话等.它们都可以用`Spring Boot`的开发风格做到一键启动和部署.

> `Spring Boot`只是将各家公司成熟的服务框架组合起来,通过`Spring Boot`封装屏蔽了复杂的配置和实现原理.最终提供给开发人员一套简单易懂,易部署和易维护的分布式系统开发工具包.

---
`Spring Cloud`是分布式微服务架构下的一站式解决方案,是各个微服务架构落地技术的集合体,俗称微服务全家桶.

## 1 Spring Cloud与Spring Boot的关系

- `Spring Boot`是开发独立的微服务,`Spring Cloud`是多个微服务的集合体
- `Spring Cloud`依赖于`Spring Boot`,`Spring Boot`不依赖`Spring Cloud`

## 2 Spring Cloud和Dubbo对比

- `Spring Cloud`社区活跃度更高
- `Spring Cloud`采用`Rest API`进行服务调用
- `Dubbo`构建自由度高,`Spring Cloud`是`Spring Source`的整合

-| `Dubbo` | `Spring Coud`
---|---|---
服务注册中心|`Zookeeper`|`Spring Cloud Netflix Eureka`
服务调用方式|`RPC`|`Rest API`
服务监控|`Dubbo Monitor`|`Spring Boot Admin`
断路器|不完善|`Spring Cloud Netflix Hystrix`
服务网关|无|`Spring Cloud Netflix Zuul`
分布式配置|无|`Spring Cloud Config`
服务跟踪|无|`Spring Cloud Sleuth`
消息总线|无|`Spring Cloud Bus`
数据流|无|`Spring Cloud Stream`
批量任务|无|`Spring Cloud Task`
---

> `Spring Cloud`微服务框架下的一站式解决方案,`Dubbo`是一款`RPC`框架

## 3 参考


- [`Spring Cloud`中文网](https://springcloud.cc/)
- [`Spring Cloud`中文社区](http://springcloud.cn/)

# 四、微服务环境搭建

> Maven分包

父工程`microservicecloud`之下有3个子工程:
- `microservicecloud-api`: 封装`Entity/接口/公共配置`
- `microservicecloud-provider-dept-8001`: 服务提供者
- `microservicecloud-consumer-dept-80`: 服务消费者

## 1 创建父工程:

- 创建工程: `Maven Project` ==> `group Id: com.qpf.cloud` ==> `artifact Id: microservicecloud` ==> `packaging: pom` ==> `Finish`

- 编辑`pom.xml`文件,提取子模块依赖
```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8<maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <type>pom</type>
            <version>Dalston.SR1</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring.boot.dependencies</artifactId>
            <type>pom</type>
            <version>1.5.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.0.4</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.31</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.0</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 2 创建公共子模块`microservicecloud-api`

- 创建工程: `Maven Module` ==> `group Id: com.qpf.microservicecloud` ==> `artifact Id: microservicecloud-api` ==> `packaging: jar` ==> `Finish`
- 编写`pom.xml`文件

```
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

- 编写实体类`Entity`,并结合`lombok`生成成员方法,实体类必须序列化

```
@AllArgsConstructor // 全参构造方法
@NoArgsConstructor  // 无参构造方法
@Data               // getter setter
@Accessors(chain=true) //链式访问
public class Department implements Serializable {
    private long id;
    private String name;
    private String db_source;
    
    // constractor getter setter toString
}
```

## 3 创建`Provider`工程

- 创建工程: `Maven Module` ==> `group Id: com.qpf.microservicecloud` ==> `artifact Id: microservicecloud-provider-dept-8001` ==> `packaging: jar` ==> `Finish`
- 编写`pom.xml`文件

```
<dependencies>
    <dependency>
        <groupId>com.qpf.microservicecloud</groupId>
        <artifactId>microservicecloud-api</artifactId>
        <version>${project.version}</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>springloaded</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```

- 编写配置文件`application.yaml`

```
server:
    port: 8001
mybatis:
    config-location: classpath:mybatis/mybatis.cfg.xml      # mybatis配置文件路径
    type-aliases-package: com.qpf.springcloud.entities      # 所以Entity别名类所在包
    mapper-locations: [classpath:mybatis/mapper/**/*.xml]   # mapper映射文件
spring:
    application:
        name: microservicecloud-dept
    datasource:
        type: com.alibaba.durid.pool.DuridDataSource        # 数据源操作类型
        diriver-class-name: org.gjt.mm.mysql.Driver         # mysql驱动包
        url: jdbc:mysql://localhost:3306/cloudDB01          # 数据库名称
        username: root
        password: root
        dbcp2:
            min-idle: 5                                     # 数据库最小维持连接数
            initial-size: 5                                 # 初始化连接数
            max-total: 5                                    # 最大连接数
            max-wait-millis: 200                            # 等待获取连接最大超时时间
```

- 创建`mybatis`配置文件`mybatis.cfg.xml`

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="cacheEnable" value="true"></setting>
    </settings>
</configuration>
```

- 执行数据库脚本

```
DROP DATABASE cloudDB01;
CREATE DATABASE cloudDB01 CHARACTER SET UTF8;
USE cloudDB01;
CREATE TABLE department (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(60),
db_source VARCHAR(60)
);
INSERT INTO department(name, db_source) VALUES('开发部',DATABASE());
INSERT INTO department(name, db_source) VALUES('测试部',DATABASE());
INSERT INTO department(name, db_source) VALUES('运维部',DATABASE());

SELECT * FROM department;
```

- 创建`dao`接口,添加`@Mapper`注解

```
public interface DepartmentDao {
    public boolean addDepartment(Department department);
    public Department findById(Long id);
    public List<Department> findAll();
}
```

- 创建对应`Entity`类的`mapper`文件

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.qpf.springcloud.dao.DepartmentDao">
    <select id="findById" resultType="Department" parameterType="Long">
        SELECT id, name, db_source FROM department WHERE id=#{id};
    </select>
    <select id="findAll" resultType="Department">
        SELECT id, name, db_source FROM department;
    </select>
    <insert id="addDepartment" parameterType="Department">
        INSERT INTO department(name, db_source) VALUES(#{name}, DATABASE());
    </insert>
</mapper>
```

- 编写`service`接口以及实现

```
# com.qpf.springcloud.service
public interface DepartmentService{
    public boolean add(Department department);
    public Department get(Long id);
    public List<Department> list();
}
# com.qpf.springcloud.service.impl
@Service
public class DepartmentServiceImpl{
    @Autowired
    private DepartmentDao dao;
    public boolean add(Department department){
        return dao.addDepartment(department);
    }
    public Department get(Long id){
        return dao.findById(id);
    }
    public List<Department> list(){
        return dao.findAll();
    }
}
```

- 创建`Controller`

```
@RestController
public class DepartmentController{
    @Autowired
    private DepartmentService service;
    
    @PostMapping("/dept/add")
    public boolean add(@RequestBody Department department) {
        return service.add(department);
    }
    
    @GetMapping("/dept/get/{id}")
    public Department get(@PathVariable("id") Long id) {
        return service.get(id);
    }
    
    @GetMapping("/dept/list")
    public List<Department> list() {
        return service.list();
    }
}
```

- 创建主程序类

```
@SpringBootApplication
public class Department_Provider_8001_App {
    public static void main(String[] args){
        SpringApplication.run(Department_Provider_8001_App.class, args);
    }
}
```


## 4 创建`Consumer`工程

- 创建工程: `Maven Module` ==> `group Id: com.qpf.microservicecloud` ==> `artifact Id: microservicecloud-consumer-dept-80` ==> `packaging: jar` ==> `Finish`
- 编写`pom.xml`文件

```
<dependencies>
    <dependency>
        <groupId>com.qpf.microservicecloud</groupId>
        <artifactId>microservicecloud-api</artifactId>
        <version>${project.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>springloaded</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```

- 编写配置文件`application.yaml`

```
server:
    port: 80
```

- `Spring`容器中添加`RestTemplate`组件

```
@Configuration
public class CloudConfig{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

> `RestTemplate`是远程访问`REST`服务的客户端模板工具集

- 创建`Controller`,使用`RestTemplate`消费服务

```
@RestController
public class DepartmentConsumer_Controller{
    @Autowired
    private RestTemplate restTemplate;
    
    private static final String REST_URL_PREFIX = "http://localhost:8001";
    
    @PostMapper("/consumer/dept/add")
    public boolean add(@RequestBody Department department){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", department, Bealean.class);
    }
    
    @GetMapper("/consumer/dept/get/{id}")
    public Department get(@PathVariable Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Department.class);
    }
    
    @GetMapper("/consumer/dept/list")
    public List<Department> list(@PathVariable Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }
}
```

- 创建主程序类

```
@SpringBootApplication
public class Department_Consumer_80_App {
    public static void main(String[] args){
        SpringApplication.run(Department_Consumer_80_App.calss, args);
    }
}
```

# 五 Eureka服务注册与发现

`Eureka`是`NetFlix`的核心模块之一,基于`Rest`服务,用于定位服务,以实现云端中间层服务发现和故障转移.使用服务标识符访问服务.

`Eureka`采用`CS`架构:
- `Eureka Server`作为服务注册功能的服务器,是服务注册中心.
    - 节点启动之后,在`Eureka Server`中注册,服务节点信息保存在`Eureka Server`的服务注册表中.
- 系统中的微服务使用`Eureka Client`连接`Eureka Server`并维持心跳连接
    - 客户端有一个内置的使用轮循(`round-robin`)算法的负载均衡器,在服务启动后,向服务器发心跳(默认周期`30s`),如果服务器在若干个周期内没有收到某个节点的心跳,服务器将会将该服务从服务注册表中移除(默认`90s`).