package xu.all.designPattern.singletonPattern;

/**
 * @Description: 单例模式
 * 确保只能创建一个对象
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
public class MainClass {
    public static void main(String[] args) {
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
        eagerSingleton.hello();

        LazySingleton lazySingleton = LazySingleton.getInstance();
        lazySingleton.hello();

        LazySingletonDoubleCheck lazySingletonDoubleCheck = LazySingletonDoubleCheck.getInstance();
        lazySingletonDoubleCheck.hello();
    }
}
