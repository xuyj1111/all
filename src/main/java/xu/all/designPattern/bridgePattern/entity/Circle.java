package xu.all.designPattern.bridgePattern.entity;

import xu.all.designPattern.bridgePattern.abstr.DrawAPI;
import xu.all.designPattern.bridgePattern.abstr.Shape;

public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
