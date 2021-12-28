package xu.all.designPattern.factoryPattern.simpleFactoryPattern;

import xu.all.designPattern.factoryPattern.simpleFactoryPattern.factory.ShapeFactory;

/**
 * @Description: 简单工厂模式
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
public class MainClass {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        factory.getShape("Square").draw();
        factory.getShape("Rectangle").draw();
    }
}
