package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xu.all.service.jdbc.JdbcService;

@Slf4j
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    private JdbcService jdbcService;

    @GetMapping("/{id}")
    public String getName(@PathVariable("id") Long id) {
        log.info(">>> request get name by id [{}]", id);
        return jdbcService.getName(id);
    }
}
