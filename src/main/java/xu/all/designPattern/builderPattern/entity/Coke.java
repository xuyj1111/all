package xu.all.designPattern.builderPattern.entity;

import xu.all.designPattern.builderPattern.abstr.ColdDrink;

public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 7.0f;
    }
}
