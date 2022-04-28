package xu.all.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import xu.all.jdk.multithread.MyTaskDecorator;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncExecutorConfig {

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
}
