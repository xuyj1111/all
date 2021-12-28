package xu.all.designPattern.builderPattern.abstr;

import xu.all.designPattern.builderPattern.entity.Wrapper;

public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
