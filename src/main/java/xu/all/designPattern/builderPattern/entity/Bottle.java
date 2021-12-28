package xu.all.designPattern.builderPattern.entity;

import xu.all.designPattern.builderPattern.abstr.Packing;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
