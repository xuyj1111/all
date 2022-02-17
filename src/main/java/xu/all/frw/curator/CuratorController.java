package xu.all.frw.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/curator")
public class CuratorController {

    @Autowired
    private CuratorFramework client;

    @PostMapping
    public void createEphemeral(@RequestParam("path") String path, @RequestParam("value") String value) {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(path, value.getBytes());
        } catch (Exception e) {
            log.error("系统异常", e);
        }
    }
}
