package xu.all.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope   //动态刷新配置（远程）
@RestController
@RequestMapping("/hello_world")
public class HelloWorldController {

    @Value("${test}")
    private String testValue;

    @Value("${test2}")
    private String test2Value;

    @PostMapping
    public String helloWorld() {
        System.out.println("test: " + testValue);
        System.out.println("test2: " + test2Value);
        return "Hello World!";
    }
}
