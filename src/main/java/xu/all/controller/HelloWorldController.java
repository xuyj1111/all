package xu.all.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello_world")
public class HelloWorldController {

    @PostMapping
    public String helloWorld() {
        return "Hello World!";
    }
}
