package xu.all.designPattern.builderPattern;

import xu.all.designPattern.builderPattern.abstr.Item;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.print("name:" + item.name() + ", ");
            System.out.print("price:" + item.price() + ", ");
            System.out.println("packing:" + item.packing().pack());
        }
    }
}
