package xu.all.designPattern.proxyPattern.jdkDynamic;

import java.lang.reflect.Proxy;

public class MainClass {

    public static void main(String[] args) {
        //被代理类
        RealSubject realSubject = new RealSubject();
        //动态代理类
        InvocationHandlerDemo handler = new InvocationHandlerDemo(realSubject);
        /** 
         * @Description: 创建代理对象
         * 第一个参数：使用handler的ClassLoader加载代理对象
         * 第二个参数：被代理类所实行的接口（所以jdk动态代理需要被代理类一定要是`实现接口的类`），可以将代理对象强转为任意一个接口（此例子中只有一个Subject接口）
         * 第三个参数：动态代理类对象
         */ 
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
        //动态生成的代理对象，输出为`com.sun.proxy.$Proxy0`，格式为以$开头，proxy 为中，最后一个数字表示对象的标号
        System.out.println(subject.getClass().getName());
        subject.hello("world");
        System.out.println(subject.bye());
    }
}
