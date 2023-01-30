package xu.all.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xu.all.spring.bean.MyBeanNameAware;

@Configuration
public class BeanFactory {

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
