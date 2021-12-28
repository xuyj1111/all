package xu.all.designPattern.prototypePattern;

public class Shape implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
