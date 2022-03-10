package xu.all.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/** 
* @Description: 监听 ServletContext 对象的生命周期【需要在启动类配置 @ServletComponentScan】
* @Author: xuyujun
* @Date: 2022/3/8 
*/ 
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context destroyed");
    }
}
