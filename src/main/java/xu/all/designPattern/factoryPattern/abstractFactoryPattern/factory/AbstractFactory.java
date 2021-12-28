package xu.all.designPattern.factoryPattern.abstractFactoryPattern.factory;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Color;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Shape;

public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
