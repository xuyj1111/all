package xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Color;

public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("filling Green...");
    }
}