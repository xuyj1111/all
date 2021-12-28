package xu.all.designPattern.singletonPattern;

/**
 * @Description: 懒汉式（同步方法）
 * 效率太低，每次getInstance都要同步
 * @Author: xuyujun
 * @Date: 2021/10/8
 */
public class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton() {
    }

    //synchronized解决线程不安全问题
    public static synchronized LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

    public void hello() {
        System.out.println("Hello LazySingleton");
    }
}
