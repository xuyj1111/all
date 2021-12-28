package xu.all.frw.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @Description: a job
 * @Author: xuyujun
 * @Date: 2021/9/15
 * 也可继承QuartzJobBean，QuartzJobBean对job简单的实现
 */
public class SendMessageJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("sending message...");
    }
}
