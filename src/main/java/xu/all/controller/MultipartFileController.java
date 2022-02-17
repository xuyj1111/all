package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/multipartFile")
public class MultipartFileController {

    @PostMapping("/sout")
    public void sout(@RequestParam(value = "file") MultipartFile file) {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
    }

    @PostMapping("/copy")
    public void copy(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "path") String path) {
        try {
            file.transferTo(Paths.get(path));
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }
}
