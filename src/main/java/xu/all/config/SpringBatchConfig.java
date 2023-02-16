package xu.all.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xu.all.spring.batch.MessageMigrationJobConfiguration;

/**
 * @Description: spring batch job factory
 * @Author: xuyujun
 * @Date: 2022/2/21
 * @EnableBatchProcessing: 是打开Batch。如果要实现多Job的情况，需要把EnableBatchProcessing注解的modular设置为true，让每个Job使用自己的ApplicationConext
 */
@Configuration
@EnableBatchProcessing(modular = true)
public class SpringBatchConfig {

    @Bean
    public ApplicationContextFactory messageMigrationJobContext() {
        return new GenericApplicationContextFactory(MessageMigrationJobConfiguration.class);
    }
}
