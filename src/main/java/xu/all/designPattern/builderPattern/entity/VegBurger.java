package xu.all.designPattern.builderPattern.entity;

import xu.all.designPattern.builderPattern.abstr.Burger;

public class VegBurger extends Burger {
    @Override
    public String name() {
        return "VegBurger";
    }

    @Override
    public float price() {
        return 8.0f;
    }
}
