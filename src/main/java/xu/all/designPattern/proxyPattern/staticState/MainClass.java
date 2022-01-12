package xu.all.designPattern.proxyPattern.staticState;

/**
 * @Description: 代理模式
 * 一个类代替另一个类的功能
 * ————
 * ProxyImage代理RealImage，实现display方法
 * @Author: xuyujun
 * @Date: 2022/1/12
 */
public class MainClass {
    public static void main(String[] args) {
        Image image = new ProxyImage("hello.jpg");
        //图片从磁盘中加载
        image.display();
        System.out.println();
        //图片不需要从磁盘中加载
        image.display();
    }
}
