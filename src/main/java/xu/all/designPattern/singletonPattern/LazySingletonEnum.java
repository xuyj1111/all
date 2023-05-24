package xu.all.designPattern.singletonPattern;

public enum LazySingletonEnum {
    INSTANCE;
    public void hello() {
        System.out.println("Hello LazySingletonEnum");
    }
}
