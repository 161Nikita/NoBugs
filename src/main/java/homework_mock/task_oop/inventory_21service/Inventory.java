package homework_mock.task_oop.inventory_21service;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, Integer> inventory = new HashMap<>();

    // добавить предмет в инвентарь

    public void addItem(String item, int quantity) {

        int oldQuantity = inventory.getOrDefault(item, 0);

        inventory.put(item, quantity + oldQuantity);

    }

    // использовать предмет
    public void useItem(String item) {
        if (!inventory.containsKey(item)) return;

        int countItem = inventory.getOrDefault(item, 0) - 1;

        if (countItem == 0) {
            inventory.remove(item);
        } else {
            inventory.put(item,countItem);
        }
    }

    // узнать количество предмета
    public int countItem(String item) {
        return inventory.getOrDefault(item, 0);
    }

    // проверить, есть ли предмет в инвентаре
    public boolean getItem(String item) {
        return inventory.containsKey(item);
    }

    // показать количество разных предметов
    public int getAllItem() {
        return inventory.size();
    }

}
