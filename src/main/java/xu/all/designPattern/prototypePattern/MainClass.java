package xu.all.designPattern.prototypePattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 原型模式
 * 创建当前对象的克隆，保证性能
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
@Slf4j
public class MainClass {
    public static void main(String[] args) {
        Shape shape = new Shape();
        Shape shapeError = shape;
        Shape shapeClone = null;
        try {
            shapeClone = (Shape) shape.clone();
        } catch (CloneNotSupportedException e) {
            log.error("系统异常", e);
        }
        System.out.println(shape.hashCode());
        System.out.println(shapeError.hashCode());
        System.out.println(shapeClone.hashCode());
    }
}
