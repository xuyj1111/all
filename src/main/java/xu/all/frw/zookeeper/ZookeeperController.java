package xu.all.frw.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {

    @Autowired
    private ZookeeperApi zookeeperApi;

    @PostMapping
    public void createEphemeralNode(@RequestParam("path") String path, @RequestParam("value") String value) {
        zookeeperApi.createEphemeralNode(path, value);
    }
}
