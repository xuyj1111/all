package xu.all.spring.bean;

import lombok.Getter;
import org.springframework.beans.factory.BeanNameAware;

public class MyBeanNameAware implements BeanNameAware {
    @Getter
    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
