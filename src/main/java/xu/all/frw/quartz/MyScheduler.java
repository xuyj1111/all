package xu.all.frw.quartz;

import org.joda.time.DateTime;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @Description: quartz计划表
 * @Author: xuyujun
 * @Date: 2021/9/15
 */
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(SendMessageJob.class)
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例,每隔1s执行一次
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
//                .startNow()//立即生效
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(1)//每隔1s执行一次
//                        .repeatForever())//一直执行
//                .build();

        //按新的cronExpression表达式构建一个新的trigger
        Date start = DateTime.now().toDate();
        Date end = DateTime.now().plusDays(1).toDate();
        String cronExpression = "1-5 * * * * ? ";
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .withSchedule(scheduleBuilder)
                .startAt(start)
                .endAt(end)
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        //睡眠
//        TimeUnit.SECONDS.sleep(5);
//        scheduler.shutdown();
//        System.out.println("--------scheduler shutdown ! ------------");
    }
}
