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
}
