package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xu.all.entity.mybatis.MybatisPlus;
import xu.all.service.mybatis.MybatisPlusService;

@Slf4j
@RestController
@RequestMapping("/mybatis_plus")
public class MybatisPlusController {

    @Autowired
    private MybatisPlusService service;

    @GetMapping("/{id}")
    public MybatisPlus getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/name")
    public MybatisPlus getByName(@RequestParam("name") String name) {
        return service.getByName(name);
    }

    @DeleteMapping("/{id}")
    public Boolean removeById(@PathVariable("id") Long id) {
        return service.removeById(id);
    }

    @PostMapping
    public Boolean save(@RequestBody MybatisPlus mybatisPlus) {
        return service.save(mybatisPlus);
    }

    @PutMapping("/{id}")
    public Boolean updateById(@PathVariable Long id,
                              @RequestBody MybatisPlus mybatisPlus) {
        MybatisPlus entity = service.getById(id);
        entity.setName(mybatisPlus.getName());
        return service.updateById(entity);
    }
}
