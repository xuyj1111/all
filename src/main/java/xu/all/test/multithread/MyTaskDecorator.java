package xu.all.test.multithread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.UUID;

/**
* @Description: 自定义装饰类
* @Author: xuyujun
* @Date: 2021/11/13
*/
@Slf4j
public class MyTaskDecorator implements TaskDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTaskDecorator.class);

    @Override
    public Runnable decorate(Runnable runnable) {
        return () -> {
            MDC.put("requestId", UUID.randomUUID().toString());
            try {
                LOGGER.info("This is a log message.");
                runnable.run();
            } catch (Exception e) {
                LOGGER.error("系统异常", e);
                System.out.println("error! error!");
            } finally {
                MDC.clear();
            }
        };
    }
}
