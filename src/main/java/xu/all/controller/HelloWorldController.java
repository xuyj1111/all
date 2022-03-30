package xu.all.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RefreshScope   //动态刷新配置（远程）
@Controller     //重定向不能用 @RestController，因此返回的需要是视图【此处没有配置视图，因此postman显示404】
@RequestMapping("/hello_world")
public class HelloWorldController {

    @Value("${test}")
    private String testValue;

    @Value("${test2}")
    private String test2Value;

    @GetMapping
    public String helloWorld() {
        System.out.println("test: " + testValue);
        System.out.println("test2: " + test2Value);
        return "Hello World!";
    }

    /** 
    * @Description: 同样可以使用'redirect:'进行重定向，但是默认为GET请求，需要用其他方式配置
    */ 
    @RequestMapping(value = "/forward", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json", "application/text"})
    public String helloWorldForward() {
        System.out.println("hello advance team");
        return "forward:" + "/hello_world";
    }
}
