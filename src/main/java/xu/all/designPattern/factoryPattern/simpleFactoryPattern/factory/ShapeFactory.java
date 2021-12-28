package xu.all.designPattern.factoryPattern.simpleFactoryPattern.factory;

import xu.all.designPattern.factoryPattern.simpleFactoryPattern.abstr.Shape;
import xu.all.designPattern.factoryPattern.simpleFactoryPattern.entity.Rectangle;
import xu.all.designPattern.factoryPattern.simpleFactoryPattern.entity.Square;

public class ShapeFactory {

    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case "Square":
                return new Rectangle();
            case "Rectangle":
                return new Square();
            default:
                return null;
        }
    }
}
