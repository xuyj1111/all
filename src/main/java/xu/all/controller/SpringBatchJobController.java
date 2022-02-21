package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: 执行spring batch的job请求
 * @Author: xuyujun
 * @Date: 2022/2/21
 */
@Slf4j
@RestController
@RequestMapping("/batch")
public class SpringBatchJobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRegistry jobRegistry;

    @PostMapping("/job")
    public void executeJob(@RequestParam("jobName") String jobName) {
        log.info(">>> request execute Spring Batch job, jobName[{}]", jobName);
        JobParameters params = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        try {
            JobExecution execution = jobLauncher.run(jobRegistry.getJob(jobName), params);
            log.info("Exit Status : " + execution.getStatus());
        } catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | NoSuchJobException e) {
            log.error("execute Spring Batch job error!", e);
        }
    }


}
