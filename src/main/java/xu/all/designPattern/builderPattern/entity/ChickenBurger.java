package xu.all.designPattern.builderPattern.entity;

import xu.all.designPattern.builderPattern.abstr.Burger;

public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "ChickenBurger";
    }

    @Override
    public float price() {
        return 16.0f;
    }
}
