package xu.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xu.all.dto.DemoDTO;
import xu.all.interfaces.GroupInterface;
import xu.all.spring.bean.MyBeanNameAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

//@RefreshScope   //动态刷新远程配置，需要使用 actuator
//@Controller
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private MyBeanNameAware myBeanNameAware;

    @Value("${hello}")
    private String hello;

    @Value("${hi}")
    private String hi;

    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/config/value")
    public String getConfigValue() {
        return hello + "\n" + hi;
    }

    /**
     * @Description: 转发demo
     * 重定向需要用 @Controller，因此返回的需要是视图【此处没有配置视图，因此postman显示404】
     * 同样可以使用'redirect:'进行重定向，但是默认为GET请求，需要用其他方式配置
     */
    @RequestMapping(value = "/forward", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json", "application/text"})
    public String testForward() {
        System.out.println("hello advance team");
        return "forward:" + "/test/hello";
    }

    /**
     * @Description: 使用@Valid和限制注解
     */
    @PostMapping("/valid")
    public void testValid(@RequestBody @Valid DemoDTO dto) {
        System.out.println("dto: " + dto);
    }

    /**
     * @Description: 使用@Validated
     */
    @PostMapping("/validated")
    public void testValidated(@RequestBody @Validated({GroupInterface.class}) DemoDTO dto) {
        System.out.println("dto: " + dto);
    }

    /**
     * @Description: addCookie() 添加cookie，setCookie() 设置cookie
     */
    @RequestMapping("/cookie")
    public void testCookie(HttpServletRequest request, HttpServletResponse response) {
        if (Objects.isNull(request.getCookies())) {
            Cookie cookie = new Cookie("respAddCK", "aaaaa");
            response.addCookie(cookie);
        }
    }

    /**
     * @Description: session的使用
     */
    @RequestMapping("/session")
    public void testSession(HttpServletRequest request) {
        // 当前有"JSESSIONID" cookie，服务器根据值自动找到对应的session；没有则新建，并在response中设置cookie
        HttpSession session = request.getSession();
        Object uuid = session.getAttribute("uuid");
        if (Objects.isNull(uuid)) {
            session.setAttribute("uuid", UUID.randomUUID().toString());
        } else {
            System.out.println(uuid);
        }
    }

    /**
     * @Description: BeanNameAware的使用
     */
    @RequestMapping("/beanNameAware")
    public String testBeanNameAware() {
        return myBeanNameAware.getBeanName();
    }

}
