package xu.all.designPattern.factoryPattern.abstractFactoryPattern.factory;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Color;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Shape;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity.Green;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity.Red;

public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        switch (color) {
            case "Red":
                return new Red();
            case "Green":
                return new Green();
            default:
                return null;
        }
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
