package xu.all.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xu.all.dto.TestDTO;
import xu.all.interfaces.GroupInterface;

import javax.validation.Valid;

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
}
