package xu.all;

import com.cxytiandi.elasticjob.annotation.EnableElasticJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@EnableElasticJob
@ServletComponentScan
public class AllApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AllApplication.class, args);
        // 打印出所有 bean name
//        for (String name : run.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
    }

}
