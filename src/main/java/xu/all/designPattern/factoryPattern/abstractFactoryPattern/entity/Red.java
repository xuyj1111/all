package xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Color;

public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("filling Red...");
    }
}