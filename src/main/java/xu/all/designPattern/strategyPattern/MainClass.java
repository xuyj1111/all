package xu.all.designPattern.strategyPattern;

/**
 * @Description: 策略模式
 * 一个类的行为或算法可以在运行时更改
 * @Author: xuyujun
 * @Date: 2022/1/19
 */
public class MainClass {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
    }
}
