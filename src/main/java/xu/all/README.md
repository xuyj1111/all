> - 某些技术点，比如jdk8的stream流，类似于工具类一样使用，则命名为XxxDemo，类内部多个测试方法，单独运行使用
> - 某些技术点，比如数据结构，需要多个类才能实现，则运行类命名统一为MainClass，类内部一个主函数


## [algorithm](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/algorithm)
- `ArraySplit`将数组规定一个拆分大小，按顺序获取子数组
### [sort文件夹](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/algorithm/sort)
1. `BubbleSort`冒泡排序
2. `BucketSort`桶排序
3. `DataFactory`创建 10 个百内的数字，给各个排序算法用
4. `InsertionSort`插入排序
5. `QuickSort`快速排序
6. `SelectionSort`选择排序
7. `SimpleSort`简单排序

## [annotation](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/annotation)
- `SimpleAnno`自定义注解

## [config](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/config)
- `BeanFactory`bean工厂
  - `public Executor myThreadPool()`创建线程池 bean
  - `public MyBeanNameAware myBeanNameAware()`实现 spring 自带的`BeanNameAware`接口，bean 可以获取 bean 名
  - `public DataSource dataSourceTwo ()`在使用yinjihuan的elastic-job时，按照文档要创建 dataSource 的 bean 给事件追踪功能使用，但在使用 Druid 数据源时已经创建过了，bean name 为"dataSource"，因此不用再创建了【隐藏】
- `EsClientConfig`es 客户端配置类
- `MybatisPlusConfig`mybatis-plus配置类
- `ObjectMapperConfig`配置自定义DateTime类的消息转换器
- `RabbitMqConfig`rabbitMQ配置类
- `SpringBatchConfig`springBatch配置类

## [controller](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/controller)
- `EsController` -> [EsRepositoryImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/frw/elasticSearch/EsRepositoryImpl.java)
- `JdbcController` -> [JdbcService](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/jdbc/JdbcService.java)
- `JpaController` -> [JpaServiceImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/jpa/JpaServiceImpl.java)
- `MultipartFileController`requestBody 中带文件【body > form-data -> key:file value:文件】
  - `public void sout`输出文件的信息
  - `public void copy`复制文件
- `MybatisPlusController` -> [MybatisPlusService](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/mybatis/MybatisPlusService.java) -> [MybatisPlusServiceImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/mybatis/MybatisPlusServiceImpl.java) -> [MybatisPlusMapper](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/mapper/MybatisPlusMapper.java) -> [MybatisPlusMapper.xml](https://github.com/xuyj1111/all/blob/master/src/main/resources/mapper/MybatisPlusMapper.xml)
- `OkHttpController` 
  - `public void send`okHttp的同步/异步请求
- `RabbitMqController`
  - `public void bindExchangeAndQueue`绑定交换机和队列，使用`amqpAdmin`
  - `public void send`使用`rabbitTemplate`发送消息
  - `public Object receive`使用`rabbitTemplate`接收消息
- `RedissonController`
  - `public void addData`使用`rabbitTemplate`添加数据
  - `public void lockAdd`使用`redissonClient`实现分布式锁（可重入）
- `RestAssuredController`为 [RestAssuredDemo](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/frw/restAssured/RestAssuredDemo.java) 类测试使用
- `SpringBatchJobController`执行 springBatch 的 job 请求
- `TestController`
  - `public String testForward()`转发请求【需要使用 @Controller】
  - `public void testValid`使用 @Valid 和限制注解
  - `public void testValidated`使用 @Validated
  - `public void testCookie`操作 cookie
  - `public void testSession`操作 session
  - `public String testBeanNameAware`BeanNameAware 的使用

## [converter](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/converter)
- `DateTimeJacksonModule`request 和 response 的 DateTime 类的消息转换器
- `JodaTimeConverter`数据库日期字段转换为 DateTime【jpa】
- `JodaTimeHandler`数据库日期字段转换为 DateTime【mybatis】
- `MyMetaObjectHandler`mybatis-plus 执行 sql 前的处理
  - `public void insertFill`insert 语句执行前
  - `public void updateFill`update 语句执行前

## [dataStructure](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/dataStructure)
### [linkedList](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/dataStructure/linkedList)
- singlyCircularLinkedList: 单向循环列表
  - `Builder`builder 类
  - `MainClass`主函数
  - `Node`节点类
- singlyLinkedList: 单链表
  - `Builder`builder 类
  - `MainClass`主函数
  - `Node`节点类

## [designPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern)
- [adapterPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/adapterPattern) 适配器模式
- [bridgePattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/bridgePattern) 桥接模式
- [builderPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/builderPattern) 创建者模式
- [compositePattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/compositePattern) 组合模式
- [factoryPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/factoryPattern) 工厂模式
- [filterPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/filterPattern) 过滤器模式
- [prototypePattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/prototypePattern) 原型模式
- [proxyPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/proxyPattern) 代理模式
- [singletonPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/singletonPattern) 单例模式
- [strategyPattern](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/designPattern/strategyPattern) 策略模式




### 文件夹
- algorithm：算法
- config：配置类
- controller：controller类
- converter：转换类
- dataStructure：数据结构
- designPattern：设计模式
- dto：dto类
- entity：实例类（对应表）
- frw：第三方包类
- interfaces：接口
- jdk8：jdk8的练习
- listener：监听类
- mapper：for mybatis-plus
- repository：for jpa
- service：service类
- spring：spring组件
- test：测试小demo类
- utils：工具类