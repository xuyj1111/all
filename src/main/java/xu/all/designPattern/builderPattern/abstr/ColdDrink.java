package xu.all.designPattern.builderPattern.abstr;

import xu.all.designPattern.builderPattern.entity.Bottle;

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
