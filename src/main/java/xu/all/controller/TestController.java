package xu.all.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xu.all.dto.TestDTO;
import xu.all.interfaces.GroupInterface;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * @Description: 使用@Valid和限制注解
     */
    @PostMapping("/valid")
    public void testValid(@RequestBody @Valid TestDTO dto) {
        System.out.println("dto: " + dto);
    }

    /**
     * @Description: 使用@Validated
     */
    @PostMapping("/validated")
    public void testValidated(@RequestBody @Validated({GroupInterface.class}) TestDTO dto) {
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

}
