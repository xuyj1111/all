package xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Shape;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("drawing Rectangle...");
    }
}
