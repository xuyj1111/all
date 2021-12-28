package xu.all.designPattern.bridgePattern;

import xu.all.designPattern.bridgePattern.abstr.Shape;
import xu.all.designPattern.bridgePattern.entity.Circle;
import xu.all.designPattern.bridgePattern.entity.GreenCircle;
import xu.all.designPattern.bridgePattern.entity.RedCircle;

/**
 * @Description: 桥接模式
 * 将抽象部分与实现部分分离，使它们都可以独立的变化
 * @Author: xuyujun
 * @Date: 2021/10/18
 */
public class MainClass {
    public static void main(String[] args) {

        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
