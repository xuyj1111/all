package xu.all.designPattern.factoryPattern.simpleFactoryPattern.entity;


import xu.all.designPattern.factoryPattern.simpleFactoryPattern.abstr.Shape;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("drawing Square...");
    }
}
