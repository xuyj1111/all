package xu.all.test.multithread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

/**
* @Description: 自定义装饰类
* @Author: xuyujun
* @Date: 2021/11/13
*/
@Slf4j
public class MyTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("系统异常", e);
                System.out.println("error! error!");
            }
        };
    }
}
