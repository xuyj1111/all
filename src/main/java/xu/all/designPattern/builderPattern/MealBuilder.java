package xu.all.designPattern.builderPattern;

import xu.all.designPattern.builderPattern.entity.ChickenBurger;
import xu.all.designPattern.builderPattern.entity.Coke;
import xu.all.designPattern.builderPattern.entity.Pepsi;
import xu.all.designPattern.builderPattern.entity.VegBurger;

public class MealBuilder {

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
