package xu.all.designPattern.singletonPattern;

/**
 * @Description: 饿汉式
 * 类装载的时候就完成实例化，会造成内存浪费
 * @Author: xuyujun
 * @Date: 2021/10/8
 */
public class EagerSingleton {
    //构造器私有化，防止直接new
    private EagerSingleton() {
    }

    private static EagerSingleton instance = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return instance;
    }

    public void hello() {
        System.out.println("Hello EagerSingleton");
    }
}
