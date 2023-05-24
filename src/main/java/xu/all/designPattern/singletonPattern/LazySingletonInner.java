package xu.all.designPattern.singletonPattern;

public class LazySingletonInner {

    private LazySingletonInner() {

    }

    private static class LazySingletonHolder {
        private static final LazySingletonInner INSTANCE = new LazySingletonInner();
    }

    public static LazySingletonInner getInstance() {
        return LazySingletonHolder.INSTANCE;
    }

    public void hello() {
        System.out.println("Hello LazySingletonInner");
    }
}
