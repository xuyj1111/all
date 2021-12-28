package xu.all.designPattern.factoryPattern.abstractFactoryPattern;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.factory.AbstractFactory;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.factory.FactoryProducer;

/**
 * @Description: 抽象工厂模式
 * 与工厂模式的区别：工厂模式只生产一种东西，只是有不同的厂商；而抽象工厂生产多个东西
 * 即AbstractFactory只有一个create方法，既然如此AbstractFactory名字得改成“物品Factory”，实现类为“厂商物品Factory”
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
public class MainClass {
    public static void main(String[] args) {
        FactoryProducer producer = new FactoryProducer();
        AbstractFactory colorFactory = producer.getFactory("Color");
        colorFactory.getColor("Red").fill();
        colorFactory.getColor("Green").fill();

        AbstractFactory shapeFactory = producer.getFactory("Shape");
        shapeFactory.getShape("Rectangle").draw();
        shapeFactory.getShape("Square").draw();
    }
}
