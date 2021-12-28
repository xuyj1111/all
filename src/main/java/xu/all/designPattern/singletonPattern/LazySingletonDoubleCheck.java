package xu.all.designPattern.singletonPattern;

/**
 * @Description: 懒加载（双重检查）
 * @Author: xuyujun
 * @Date: 2021/10/8
 */
public class LazySingletonDoubleCheck {

    private static volatile LazySingletonDoubleCheck singleton;

    private LazySingletonDoubleCheck() {
    }

    public static LazySingletonDoubleCheck getInstance() {
        if (singleton == null) {
            synchronized (LazySingletonDoubleCheck.class) {
                if (singleton == null) {
                    singleton = new LazySingletonDoubleCheck();
                }
            }
        }
        return singleton;
    }

    public void hello() {
        System.out.println("Hello LazySingletonDoubleCheck");
    }
}
