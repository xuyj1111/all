package xu.all.designPattern.prototypePattern;

/**
 * @Description: 原型模式
 * 创建当前对象的克隆，保证性能
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
public class MainClass {
    public static void main(String[] args) {
        Shape shape = new Shape();
        Shape shapeError = shape;
        Shape shapeClone = null;
        try {
            shapeClone = (Shape) shape.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(shape.hashCode());
        System.out.println(shapeError.hashCode());
        System.out.println(shapeClone.hashCode());
    }
}
