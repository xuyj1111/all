package xu.all.designPattern.builderPattern;

/**
 * @Description: 建造者模式
 * 使用多个简单的对象一步一步构建成一个复杂的对象
 * @Author: xuyujun
 * @Date: 2021/10/9
 */
public class MainClass {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();

        System.out.println("vegMeal:");
        vegMeal.showItems();
        System.out.println("cost:" + vegMeal.getCost());

        System.out.println("nonVegMeal:");
        nonVegMeal.showItems();
        System.out.println("cost:" + nonVegMeal.getCost());
    }
}
