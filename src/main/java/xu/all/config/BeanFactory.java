package xu.all.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import xu.all.spring.bean.MyBeanNameAware;
import xu.all.test.multithread.MyTaskDecorator;

import java.util.concurrent.Executor;

@Configuration
public class BeanFactory {

    /**
     * @Description: 创建线程池，为异步使用
     */
    @Bean
    public Executor myThreadPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(60);
        executor.setThreadNamePrefix("myThreadPool: ");
        executor.setTaskDecorator(new MyTaskDecorator());
        executor.initialize();
        return executor;
    }

    @Bean("myBNA")
    public MyBeanNameAware myBeanNameAware(){
        return new MyBeanNameAware();
    }

    /** 
     * @Description: 在使用yinjihuan的elastic-job时，
     * 按照文档要创建 dataSource 的 bean 给事件追踪功能使用，
     * 但在使用 Druid 数据源时已经创建过了，bean name 为"dataSource"，因此不用再创建了
     */
//    @Bean("dataSource")
//    @ConfigurationProperties("spring.datasource")
//    public DataSource dataSourceTwo (){
//        return DruidDataSourceBuilder.create().build();
//    }

}
