package xu.all.frw.okHttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OkHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkHttpApplication.class, args);
    }

}
