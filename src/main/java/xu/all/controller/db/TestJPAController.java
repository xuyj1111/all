package xu.all.controller.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xu.all.entity.TestJPA;
import xu.all.service.jpa.TestJPAService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test_jpa")
public class TestJPAController {

    @Autowired
    private TestJPAService testJPAService;

    @GetMapping("/{id}")
    public TestJPA getOne(@PathVariable("id") Long id) {
        return testJPAService.getOne(id);
    }

    @PostMapping("/batch")
    public ResponseEntity batchCreate(@RequestBody List<TestJPA> list) {
        log.info(">>> request batch create test info, list [{}]", list);
        testJPAService.batchCreate(list);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<TestJPA> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = "5") Integer size,
                              @RequestParam(value = "sortField", required = false) String sortField,
                              @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction) {
        Sort sort = Sort.unsorted();
        if (StringUtils.isNotBlank(sortField)) {
            sort = Sort.by(direction, sortField);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return testJPAService.findAll(pageable);
    }
}
