package xu.all.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.DelegateExpressionFieldInjectionMode;
import org.activiti.engine.impl.cfg.TransactionPropagation;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import xu.all.frw.activiti.CustomDelegateInterceptor;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

    @Value("${activiti.asyncExecutor.corePoolSize:2}")
    private int asyncExecutorCorePoolSize;

    @Value("${activiti.asyncExecutor.maxPoolSize:10}")
    private int asyncExecutorMaxPoolSize;

    @Value("${activiti.asyncExecutor.threadPoolQueueSize:100}")
    private int asyncExecutorThreadPoolQueueSize;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager, CustomDelegateInterceptor customDelegateInterceptor) {
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
//        spec.setIdGenerator(new StrongUuidGenerator());
        spec.setDataSource(dataSource);
        spec.setTransactionManager(transactionManager);
        // 建表策略
        spec.setDatabaseSchemaUpdate(SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 异步执行器开启
        spec.setAsyncExecutorActivate(true);
        spec.setDelegateExpressionFieldInjectionMode(DelegateExpressionFieldInjectionMode.MIXED);
        spec.setAsyncExecutorCorePoolSize(asyncExecutorCorePoolSize);
        spec.setAsyncExecutorMaxPoolSize(asyncExecutorMaxPoolSize);
        spec.setAsyncExecutorThreadPoolQueueSize(asyncExecutorThreadPoolQueueSize);
        spec.setHistory(HistoryLevel.NONE.getKey());
        spec.setDelegateInterceptor(customDelegateInterceptor);
        // https://community.alfresco.com/message/820368-got-lots-of-deadlock-exception
        spec.setBulkInsertEnabled(false);
        spec.setAsyncExecutorMaxAsyncJobsDuePerAcquisition(100);
        spec.setAsyncExecutorMaxTimerJobsPerAcquisition(100);
        spec.setAsyncExecutorDefaultTimerJobAcquireWaitTime(10 * 1000);
        spec.setAsyncExecutorMessageQueueMode(true);
        spec.setDefaultCommandConfig(new CommandConfig(true, TransactionPropagation.NOT_SUPPORTED));
        return spec;
    }

    @Bean
    public CustomDelegateInterceptor customDelegateInterceptor() {
        return new CustomDelegateInterceptor();
    }

    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngineFactoryBean;
    }

    @Bean
    ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    /** 
     * @Description: 执行管理，包括启动、推进、删除流程实例等操作
     */ 
    @Bean
    RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    /** 
     * @Description: 任务管理
     */ 
    @Bean
    TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    /**
     * @Description: 历史管理(执行完的数据的管理)
     */
    @Bean
    HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    /**
     * @Description: 管理流程定义
     */
    @Bean
    RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    /**
     * @Description: 一个可选服务，任务表单管理
     */
    @Bean
    FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    /**
     * @Description: 组织机构管理
     */
    @Bean
    IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }
}
