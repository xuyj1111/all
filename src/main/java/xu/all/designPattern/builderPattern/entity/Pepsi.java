package xu.all.designPattern.builderPattern.entity;

import xu.all.designPattern.builderPattern.abstr.ColdDrink;

public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 7.5f;
    }
}
