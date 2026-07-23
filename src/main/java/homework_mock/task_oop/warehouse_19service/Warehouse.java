package homework_mock.task_oop.warehouse_19service;


import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    Map<String, Integer> product = new HashMap<>();

    // добавить товар на склад
    public void addProduct(String name, int quantity) {
        // получаем количество товара
        int oldQuantity = product.getOrDefault(name, 0);
        // если товар, был, то прибавляем количество переданное, если не был, то создаем новый товар с переданным количеством
        product.put(name, oldQuantity + quantity);
    }

    // уменьшить количество товара
    public void minusProduct(String name, int quantity) {

        int currentQuantity = product.getOrDefault(name, 0);

        // если передаваемое количество на уменьшение товара больше чем есть на складе, то сообщим об ошибке
        if (quantity > currentQuantity) {
            System.out.println("Ошибка: нельзя уменьшить количество " + name + " больше, чем есть на складе");
            return;
        }
        int newQuantity = currentQuantity - quantity;
        // удаляем товар, если он стал 0
        if (newQuantity == 0) {
            product.remove(name);
        } else {
            product.put(name, newQuantity);
        }
    }

    // узнать остаток по названию
    public int getQuantityProduct(String name) {
        return product.getOrDefault(name, 0);
    }

    // проверить, есть ли товар на складе
    public boolean getProductWarehouse(String name) {
        return product.containsKey(name);
    }

    // показать количество разных товаров
    public int countProductWarehouse() {
        return product.size();
    }
}
