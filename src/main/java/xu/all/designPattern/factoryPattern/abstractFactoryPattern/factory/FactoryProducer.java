package xu.all.designPattern.factoryPattern.abstractFactoryPattern.factory;

public class FactoryProducer {

    public AbstractFactory getFactory(String choice) {
        switch (choice) {
            case "Shape":
                return new ShapeFactory();
            case "Color":
                return new ColorFactory();
            default:
                return null;
        }
    }
}
