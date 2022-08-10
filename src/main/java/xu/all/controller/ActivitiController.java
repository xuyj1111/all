package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/activiti")
public class ActivitiController {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/engine_info")
    public void info() {
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
    }

    @PostMapping("/deploy")
    public String deploy(@RequestParam(value = "bpmn") MultipartFile file) throws IOException {
        log.debug("deploying file name [{}]", file.getOriginalFilename());
        Deployment deployment = repositoryService.createDeployment()
                .addInputStream(file.getOriginalFilename(), file.getInputStream())
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        log.info(">>> deploy successfully, process deployment id [{}]", processDefinition.getId());
        return processDefinition.getId();
    }

    @PostMapping("/startProcess/{processDefId}")
    public void startProcess(@PathVariable String processDefId) {
        log.info(">>> starting process, processDefId [{}]", processDefId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefId);
        log.info(">>> start successfully, process instance id [{}]", processInstance.getId());
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(@RequestParam String assignee) {
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
    }

    @PostMapping("/tasks/{taskId}")
    public void completeTask(@PathVariable String taskId) {
        taskService.complete(taskId);
    }
}
