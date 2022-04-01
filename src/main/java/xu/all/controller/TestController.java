package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xu.all.dto.TestDTO;
import xu.all.entity.Test;
import xu.all.interfaces.GroupInterface;
import xu.all.service.TestService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/{id}")
    public Test getOne(@PathVariable("id") Long id) {
        return testService.getOne(id);
    }

    @PostMapping("/batch")
    public ResponseEntity batchCreate(@RequestBody List<Test> list) {
        log.info(">>> request batch create test info, list [{}]", list);
        testService.batchCreate(list);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Test> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "5") Integer size,
                           @RequestParam(value = "sortField", required = false) String sortField,
                           @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction) {
        Sort sort = Sort.unsorted();
        if (StringUtils.isNotBlank(sortField)) {
            sort = Sort.by(direction, sortField);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return testService.findAll(pageable);
    }

    //*****************************以下为test请求，与test表无关*****************************

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
