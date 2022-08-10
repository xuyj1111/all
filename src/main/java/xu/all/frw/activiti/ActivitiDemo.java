package xu.all.frw.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ActivitiDemo {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void deploy() {
    }
}
