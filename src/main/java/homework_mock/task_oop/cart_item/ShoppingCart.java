package homework_mock.task_oop.cart_item;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    // Добавление: принимает продукт и количество, создает внутри CartItem
    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    // Удаление по имени продукта
    public void removeProduct(String productName) {
        items.removeIf(item -> item.getProduct().getName().equalsIgnoreCase(productName));
    }

    // Изменение количества по имени продукта + проверка правила на 0
    public void changeQuantity(String productName, int quantity) {
        if (quantity <= 0) {
            removeProduct(productName);
            return;
        }

        for (CartItem item : items) {
            if (item.getProduct().getName().equalsIgnoreCase(productName)) {
                item.changeQuantity(quantity);
                return;
            }
        }
    }

    // Общая стоимость всей корзины
    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    // Вывод содержимого на экран
    public void showItems() {
        System.out.println("В корзине:");
        for (CartItem item : items) {
            Product p = item.getProduct();
            System.out.println(p.getName() + " — " + (int)p.getPrice() + " × " + item.getQuantity());
        }
    }
}
