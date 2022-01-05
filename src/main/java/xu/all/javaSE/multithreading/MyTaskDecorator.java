package xu.all.javaSE.multithreading;

import org.springframework.core.task.TaskDecorator;

/**
* @Description: 自定义装饰类
* @Author: xuyujun
* @Date: 2021/11/13
*/
public class MyTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error! error!");
            }
        };
    }
}
