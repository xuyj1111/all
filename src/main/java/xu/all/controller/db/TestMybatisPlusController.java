package xu.all.controller.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xu.all.entity.mybatis.TestMybatisPlus;
import xu.all.service.mybatis.TestMybatisPlusService;

@Slf4j
@RestController
@RequestMapping("/test_mybatis_plus")
public class TestMybatisPlusController {

    @Autowired
    private TestMybatisPlusService service;

    @GetMapping("/{id}")
    public TestMybatisPlus getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean removeById(@PathVariable("id") Long id) {
        return service.removeById(id);
    }

    @PostMapping
    public Boolean save(@RequestBody TestMybatisPlus testMybatisPlus) {
        return service.save(testMybatisPlus);
    }

    @PutMapping("/{id}")
    public Boolean updateById(@PathVariable Long id,
                              @RequestBody TestMybatisPlus testMybatisPlus) {
        TestMybatisPlus entity = service.getById(id);
        entity.setName(testMybatisPlus.getName());
        return service.updateById(entity);
    }
}
