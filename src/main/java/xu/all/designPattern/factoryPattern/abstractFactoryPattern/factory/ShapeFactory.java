package xu.all.designPattern.factoryPattern.abstractFactoryPattern.factory;

import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Color;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.abstr.Shape;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity.Rectangle;
import xu.all.designPattern.factoryPattern.abstractFactoryPattern.entity.Square;

public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        switch (shape) {
            case "Rectangle":
                return new Rectangle();
            case "Square":
                return new Square();
            default:
                return null;
        }
    }
}
