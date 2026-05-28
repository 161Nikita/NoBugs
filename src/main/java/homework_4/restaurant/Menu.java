package homework_4.restaurant;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Dish> dishes = new ArrayList<>();

    public void addDish(Dish d) {
        dishes.add(d);
    }

    public void printMenu() {
        for (Dish dish : dishes) {
            System.out.println(dish.getDescription());
        }
    }
}
