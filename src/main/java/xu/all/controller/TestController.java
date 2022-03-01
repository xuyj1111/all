package xu.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xu.all.dto.TestDTO;
import xu.all.entity.Test;
import xu.all.interfaces.GroupInterface;
import xu.all.service.TestService;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/{id}")
    public Test getOne(@PathVariable("id") Long id) {
        return testService.getOne(id);
    }

    /**
     * @Description: 使用@Valid和限制注解
     */
    @PostMapping("/valid")
    public void testValid(@RequestBody @Valid TestDTO dto) {
        System.out.println("number: " + dto);
    }

    /**
     * @Description: 使用@Validated
     */
    @PostMapping("/validated")
    public void testValidated(@RequestBody @Validated({GroupInterface.class}) TestDTO dto) {
        System.out.println("number: " + dto);
    }
}
