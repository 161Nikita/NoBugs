package homework_mock.task_oop.shopping_10service;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Item> itemList = new ArrayList<>();

    // добавить товар в корзину
    public void addItemInShoppingCart(Item item) {
        this.itemList.add(item);
    }

    // удалить товар из корзины
    public void removeItemInShoppingCart(Item item) {
        this.itemList.remove(item);
    }

    // изменить количество товара
    public void changeQuantity(Item item, int newQuantity) {
        if (newQuantity <= 0) {
            removeItemInShoppingCart(item);
            return;
        }
        item.setQuantity(newQuantity);
    }

    // посчитать общую стоимость корзины
    public double total() {
        double total = 0;
        for (Item i : itemList) {
            total += i.getPrice() * i.getQuantity();
        }
        return total;
    }

    // показать содержимое корзины
    public void printShoppingCart() {
        System.out.println("В корзине: \n");
        for (Item i : itemList) {
            System.out.println(i);
        }
        System.out.println("\nОбщая стоимость: \n" + (int) total());
    }
}
