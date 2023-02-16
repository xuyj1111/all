package xu.all.controller;

import org.springframework.web.bind.annotation.*;
import xu.all.dto.TestDTO;

/**
 * @Description: 为RestAssuredDemo类测试使用
 * @Author: xuyujun
 * @Date: 2022/1/10
 */
@RestController
@RequestMapping("/rest_assured")
public class RestAssuredController {

    @PostMapping(value = "/query_param", produces = "application/json")
    public String queryRest(@RequestParam("key") String key,
                            @RequestParam("value") String value) {
        return key + ":" + value;
    }

    @PostMapping(value = "/path_param/{id}", produces = "application/json")
    public String pathRest(@PathVariable("id") Integer id) {
        return "id: " + id;
    }

    @PostMapping(value = "/body", produces = "application/json")
    public TestDTO bodyRest(@RequestBody TestDTO testDTO) {
        testDTO.setAge(25);
        return testDTO;
    }
}
