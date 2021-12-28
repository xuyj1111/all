package xu.all.designPattern.builderPattern.abstr;

/**
 * @Description: 表示食物条目和食物包装的接口
 * @Author: xuyujun
 * @Date: 2021/10/8
 */
public interface Item {

    public String name();

    public Packing packing();

    public float price();
}
