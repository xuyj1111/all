package xu.all.designPattern.proxyPattern.cglibDynamic;

public class Dog {
    final public void run(String name) {
        System.out.println("狗"+name+"----run");
    }

    public void eat() {
        System.out.println("狗----eat");
    }
}
