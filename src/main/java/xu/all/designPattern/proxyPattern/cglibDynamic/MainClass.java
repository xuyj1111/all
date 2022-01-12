package xu.all.designPattern.proxyPattern.cglibDynamic;

import net.sf.cglib.proxy.Enhancer;

public class MainClass {

    public static void main(String[] args) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dog.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的creat方法就是正式创建代理类
        Dog proxyDog = (Dog)enhancer.create();
        //调用代理类的eat方法，而run方法是final 不能被代理
        proxyDog.eat();
    }
}
