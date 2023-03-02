> 本人的代码笔记集，大杂烩
> - 某些技术点，类内部有多个测试方法，单独运行使用，则命名为XxxTest，
> - 某些技术点，比如数据结构，需要多个类才能实现，则运行类命名统一为MainClass，类内部一个主函数


## [algorithm](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/algorithm)
- `ArraySplit`将数组规定一个拆分大小，按顺序获取子数组
### [sort](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/algorithm/sort)
1. `BubbleSort`冒泡排序
2. `BucketSort`桶排序
3. `DataFactory`创建 10 个百内的数字，给各个排序算法用
4. `InsertionSort`插入排序
5. `QuickSort`快速排序
6. `SelectionSort`选择排序
7. `SimpleSort`简单排序

***

## [annotation](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/annotation)
- `SimpleAnno`自定义注解

***

## [config](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/config)
- `BeanFactory`bean工厂
  - `Executor myThreadPool()`创建线程池 bean
  - `MyBeanNameAware myBeanNameAware()`实现 spring 自带的`BeanNameAware`接口，bean 可以获取 bean 名
  - `DataSource dataSourceTwo ()`在使用yinjihuan的elastic-job时，按照文档要创建 dataSource 的 bean 给事件追踪功能使用，但在使用 Druid 数据源时已经创建过了，bean name 为"dataSource"，因此不用再创建了【隐藏】
- `EsClientConfig`es 客户端配置类
- `MybatisPlusConfig`mybatis-plus配置类
- `ObjectMapperConfig`配置自定义DateTime类的消息转换器
- `RabbitMqConfig`rabbitMQ配置类
- `SpringBatchConfig`springBatch配置类

***

## [controller](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/controller)
- `EsController` -> [EsRepositoryImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/frw/elasticSearch/EsRepositoryImpl.java)
- `JdbcController` -> [JdbcService](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/jdbc/JdbcService.java)
- `JpaController` -> [JpaServiceImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/jpa/JpaServiceImpl.java)
- `MultipartFileController`requestBody 中带文件【body > form-data -> key:file value:文件】
  - `void sout`输出文件的信息
  - `void copy`复制文件
- `MybatisPlusController` -> [MybatisPlusService](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/mybatis/MybatisPlusService.java) -> [MybatisPlusServiceImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/mybatis/MybatisPlusServiceImpl.java) -> [MybatisPlusMapper](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/mapper/MybatisPlusMapper.java) -> [MybatisPlusMapper.xml](https://github.com/xuyj1111/all/blob/master/src/main/resources/mapper/MybatisPlusMapper.xml)
- `OkHttpController` 
  - `void send`okHttp的同步/异步请求
- `RabbitMqController`
  - `void bindExchangeAndQueue`绑定交换机和队列，使用`amqpAdmin`
  - `void send`使用`rabbitTemplate`发送消息
  - `Object receive`使用`rabbitTemplate`接收消息
- `RedissonController`
  - `void addData`使用`rabbitTemplate`添加数据
  - `void lockAdd`使用`redissonClient`实现分布式锁（可重入）
- `RestAssuredController`为 [RestAssuredTest](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/frw/restAssured/RestAssuredTest.java) 类测试使用
- `SpringBatchJobController`执行 springBatch 的 job 请求
- `TestController`
  - `String testForward()`转发请求【需要使用 @Controller】
  - `void testValid`使用 @Valid 和限制注解
  - `void testValidated`使用 @Validated
  - `void testCookie`操作 cookie
  - `void testSession`操作 session
  - `String testBeanNameAware`BeanNameAware 的使用

***

## [converter](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/converter)
- `DateTimeJacksonModule`request 和 response 的 DateTime 类的消息转换器
- `JodaTimeConverter`数据库日期字段转换为 DateTime【jpa】
- `JodaTimeHandler`数据库日期字段转换为 DateTime【mybatis】
- `MyMetaObjectHandler`mybatis-plus 执行 sql 前的处理
  - `void insertFill`insert 语句执行前
  - `void updateFill`update 语句执行前

***

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

***

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

***

## [dto](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/dto)
- `BaseDTO`dto的基类，实现 Serializable 接口；使用 @JsonIgnoreProperties
- `TestDTO`用于 test 的 dto 类
- `TestAnnoDTO`使用自定义注解的 dto 类
- `ValidDTO`用于 TestController 中的`testValid`和`testValidated`方法使用

***

## [entity](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/entity)
### [jpa](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/entity/jpa)
- `BaseEntity`数据库实体基类
- `Jpa`jpa 表实体类
- `Message`message 表实体类
### [mybatis](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/entity/mybatis)
- `BaseEntity`数据库实体基类
- `MybatisPlus`mybatis_plus 表实体类

***

## [frw](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw)
### [awaitility](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/awaitility) ([doc](https://github.com/awaitility/awaitility/wiki/Usage))
- `AwaitilityTest`
  - `void testUntil`until 方法等待和校验
  - `void testFiled()`until 方法中引用对象字段判断
### [curator](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/curator) ([doc](https://curator.apache.org/getting-started.html))
- `CuratorConfig`curator 配置类
- `CuratorController`curator Controller 类
  - `void createEphemeral`创建临时节点
### [easyexcel](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/easyexcel) ([doc](https://alibaba-easyexcel.github.io/index.html))
- `EasyExcelWrite`写 excel
- `EasyExcelRead`读 excel demo【没完成】
### [elasticJob](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/elasticJob) ([doc](https://shardingsphere.apache.org/elasticjob/current/cn/overview/))
- `MyDataflowJob`数据流任务
- `MySimpleJob`简单任务
### [elasticSearch](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/elasticSearch) ([doc](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html))
- `EsRepository`
- `EsRepositoryImpl` <- [EsController](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/controller/EsController.java)
### [guava](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/guava) ([doc](https://github.com/google/guava/wiki))
- `GuavaTest`谷歌工具库 demo
  - `void joinerTest`拼接集合或数组为字符串 "Joiner.on(String separator)"
  - `void splitterTest`拆分字符串 "Splitter.on(String separator)"、"Splitter.fixedLength(int length)"
  - `void stringsTest`String 工具类 "Strings.XXX"
### [hamcrest](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/hamcrest) ([doc](http://hamcrest.org/JavaHamcrest/?utm_source=testingpai.com))
- `HamcrestTest`hamcrest 各种校验方法
### [jackson](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/jackson) ([doc](https://github.com/FasterXML/jackson))
- `JacksonTest`使用自己封装"ObjectMapper"的"JsonMapper"工具类
### [mockito](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/mockito) ([doc](https://javadoc.io/static/org.mockito/mockito-core/4.5.1/org/mockito/Mockito.html))
- `MockitoTest`Mockito 的使用
### [okHttp](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/okHttp) ([doc](https://square.github.io/okhttp/))
- `OkHttpTest` <- [OkHttpController](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/controller/OkHttpController.java)
### [openCsv](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/openCsv) ([doc](http://opencsv.sourceforge.net/#skipping_filtering_and_verifying))
- `OpenCsvTest`
  - `void createCsv`创建csv文件
  - `void readCsv`读取csv文件
  - `void readCsvNew`读取csv文件，通过建造者模式
  - `void remove`删除csv文件
### [orika](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/orika) ([doc](http://orika-mapper.github.io/orika-docs/index.html))
- `OrikaTest`配置映射 demo
### [quartz](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/quartz) ([doc](http://www.quartz-scheduler.org/documentation/quartz-2.3.0/))
- `MainClass`主函数，执行 quartz job
- `SendMessageJob`定义 job 内容
### [restAssured](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/restAssured) ([doc](https://rest-assured.io/#docs))
- `RestAssuredTest`
  - `void queryRest`使用"queryParam"请求
  - `void pathRest`使用"pathParam"请求
  - `void bodyRest`使用"body"请求
### [utils](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/utils)
- `StringUtilsTest`"StringUtils"工具类的使用
### [zip4j](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/zip4j) ([doc](https://github.com/srikanth-lingala/zip4j))
- `Zip4jTest`
  - `void createZip`创建zip
  - `void unZip`解压zip
  - `void remove`删除zip和文件夹
  - `void createFileAndDir`使用自己封装的"FileTool"工具类创建测试用的文件夹及文件
### [zookeeper](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/frw/zookeeper) ([doc](https://zookeeper.apache.org/doc/current/index.html))
- `WatcherApi`监听类
- `ZookeeperApi`zk 客户端实现类
  - `Stat exists(String path, boolean needWatch)`判断指定节点是否存在
  - `Stat exists(String path, Watcher watcher)`检测结点是否存在 并设置监听事件
  - `boolean createEphemeralNode(String path, String data)`创建临时节点
  - `boolean createNode(String path, String data)`创建持久化节点
  - `boolean updateNode(String path, String data)`修改持久化节点
  - `boolean deleteNode(String path)`删除持久化节点
  - `List<String> getChildren(String path)`获取当前节点的子节点(不包含孙子节点)
  - `String getData(String path, Watcher watcher)`获取指定节点的值
  
***

## [interfaces](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/interfaces)
- `FirstGroupInterface` -> [ValidDTO](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/dto/ValidDTO.java) 为 @Validated 注解使用的组接口
- `GroupInterface` -> [TestController](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/controller/TestController.java) 对 @Validated 注解的组进行排序
- `SecordGroupInterface` -> [ValidDTO](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/dto/ValidDTO.java) 为 @Validated 注解使用的组接口

***

## [jdk8](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/jdk8)
### [completableFuture](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/jdk8/completableFuture)
- `CompletableFutureTest`
  - `void testCompletableFutureSupplyAsync`使用"CompletableFuture.supplyAsync"创建有返回值异步任务；"CompletableFuture.runAsync"创建无返回值异步任务
  - `void testCompletableFutureThenApply`使用 CompletableFuture 对象的"thenApply"方法，表示某个任务执行完成后执行的动作，即回调方法；"thenApplyAsync"则是先挂起一个新线程异步执行，直到前一个任务执行完，回调返回值
  - `void testCompletableFutureThenAccept`使用 CompletableFuture 对象的"thenAccept"方法，同"thenApply"接收上一个任务的返回值作为参数，但是无返回值
### [option](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/jdk8/option)
- `OptionTest`
  - `void createOptional`创建 optional 实例
  - `void changeOptional`包装可变对象的 optional 的测试【optional对象是不可变的集合】
  - `void orElseOptional`orElse 方法的使用
### [stream](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/jdk8/stream)
- `StreamTest`
  - `void streamPeek`peek操作
  - `void streamForeach`串行流遍历
  - `void parallelStreamForeach`并行流遍历
  - `void streamMap`映射每个元素到对应的结果
  - `void streamFilter`过滤
  - `void streamLimit`限制数量
  - `void streamSort`排序
  - `void intStreamTest`IntStream流
  - `void reduceTest01`reduce的使用（一）
  - `void reduceTest02`reduce的使用（二）
  - `void reduceTest03`reduce的使用（三）

***

## [listener](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/listener)
- `ContextListener`监听 ServletContext 对象的生命周期【需要在启动类配置 @ServletComponentScan】

***

## [mapper](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/mapper)
- `MybatisPlusMapper` <- [MybatisPlusServiceImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/mybatis/MybatisPlusServiceImpl.java)

***

## [repository](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/repository)
- `JpaRepository` <- [JpaServiceImpl](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/service/jpa/JpaServiceImpl.java)

***

## [service](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/service)
### [jdbc](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/service/jdbc)
- `JdbcService` <- [JdbcController](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/controller/JdbcController.java) 使用"NamedParameterJdbcTemplate"
### [jpa](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/service/jpa)
- `JpaService`
- `JpaServiceImpl` -> [JpaRepository](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/repository/JpaRepository.java)
### [mybatis](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/service/mybatis)
- `MybatisPlusService`
- `MybatisPlusServiceImpl` -> [MybatisPlusMapper](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/mapper/MybatisPlusMapper.java)

***

## [spring](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/spring)
### [batch](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/spring/batch) ([doc](https://docs.spring.io/spring-batch/docs/current/reference/html/index.html))
- `MessageItemReadListener` -> [MessageMigrationJobConfiguration](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/spring/batch/MessageMigrationJobConfiguration.java) 监听读操作
- `MessageLineMapper` -> [MessageMigrationJobConfiguration](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/spring/batch/MessageMigrationJobConfiguration.java) 读数据后，对数据映射到 Java 对象
- `MessageMigrationJobConfiguration` 读取文件，写入数据库，批处理配置【[SpringBatchJobController](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/controller/SpringBatchJobController.java) 请求执行】
- `MessageWriteListener` -> [MessageMigrationJobConfiguration](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/spring/batch/MessageMigrationJobConfiguration.java) 监听写操作
### [bean](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/spring/bean)
- `MyBeanNameAware` -> [BeanFactory](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/config/BeanFactory.java#L31) / [TestController](https://github.com/xuyj1111/all/blob/master/src/main/java/xu/all/controller/TestController.java#L101)
### [retry](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/spring/retry) ([doc](https://docs.spring.io/spring-batch/docs/current/reference/html/retry.html))
- `RetryTest`简单重试策略和指数退避策略
### [spel](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/spring/spel)
- `SpelTest`
  - `void literalQuantityExpression`字面量表达式
  - `void arithmeticExpression`算数表达式
  - `void logicExpression`逻辑表达式
  - `void otherExpression`其他表达式
  - `void clazzTypeExpression`类类型表达式
  - `void clazzInstanceExpression`类实例化
  - `void variableExpression`变量定义及引用
  - `void functionExpression`自定义函数

***

## [test](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/test)
### [multithread](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/test/multithread)
- `MultithreadTest`
  - `void testMyThread`使用自定义 Thread
  - `void testMyRunnable`使用自定义 Runnable
  - `void testMyCallable`使用自定义 Callable
  - `void testReentrantLock`使用 ReentrantLock
  - `void testCountDownLatch`使用 CountDownLatch
  - `void testCyclicBarrier`使用 CyclicBarrier
  - `void testSemaphore`使用 Semaphore
  - `Executor createThreadPool`创建线程池
- `MyCallable`自定义 Callable
- `MyRunnable`自定义 Runnable
- `MyTaskDecorator`？？？？？？？？？？？？？？？？？？？？？？？？
### `TestClass`
- `void byteStringConvert`byte数组和string相互转换
- `void verifyURL`校验网址
- `void testPattern`Pattern的使用
- `void testAnnotation`自定义注解
### `ThreadLocalTest`
- `Executor createThreadPool`创建线程池
- `void test01ThreadLocal`最简单的ThreadLocal用法，证明了线程之间不共享threadLocal中的值
- `void test01InheritableThreadLocal` InheritableThreadLocal 实现子父线程可传递
- `void test02InheritableThreadLocal` 基本数据类型不可证明父子线程指向同一对象
- `void test03InheritableThreadLocal` InheritableThreadLocal 中子父线程中指向同一对象
- `void test04InheritableThreadLocal`线程池在 init 新的线程时，会将主线程的 InheritableThreadLocal 传递给子线程
- `void test05InheritableThreadLocal`但如果在 init 子线程时，主线程 InheritableThreadLocal 中值为 null，子线程也是 null；之后主线程被赋了新值，已经被 init 的子线程再次被调用时，主线程不会赋值给子线程（当前线程池大小为1，则可实现二次调用创建的子线程）
- `void test01TransmittableThreadLocal`解决了 InheritableThreadLocal 在线程池时父子线程传递的问题

***

## [utils](https://github.com/xuyj1111/all/tree/master/src/main/java/xu/all/utils)
- `DateTimeUtil` dateTime 工具类

***




