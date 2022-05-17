package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xu.all.entity.jpa.Jpa;
import xu.all.service.jpa.JpaService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private JpaService JPAService;

    @GetMapping("/{id}")
    public Jpa getOne(@PathVariable("id") Long id) {
        return JPAService.getOne(id);
    }

    @PostMapping("/batch")
    public ResponseEntity batchCreate(@RequestBody List<Jpa> list) {
        log.info(">>> request batch create test info, list [{}]", list);
        JPAService.batchCreate(list);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Jpa> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size,
                          @RequestParam(value = "sortField", required = false) String sortField,
                          @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction) {
        Sort sort = Sort.unsorted();
        if (StringUtils.isNotBlank(sortField)) {
            sort = Sort.by(direction, sortField);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return JPAService.findAll(pageable);
    }
}
