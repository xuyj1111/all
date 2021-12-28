package xu.all.designPattern.builderPattern.entity;

import xu.all.designPattern.builderPattern.abstr.Packing;

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
