package homework_mock.task_oop.order_9service;

/**
 * "В системе есть заказ.
 * <p>
 * У заказа есть:
 * <p>
 * номер заказа
 * список товаров
 * <p>
 * У каждого товара есть:
 * <p>
 * название
 * цена
 * количество
 * <p>
 * Нужно реализовать систему, которая может:
 * <p>
 * добавить товар в заказ
 * <p>
 * посчитать общую стоимость заказа
 * <p>
 * Формула:
 * <p>
 * стоимость товара = цена × количество
 * стоимость заказа = сумма стоимостей всех товаров
 * <p>
 * Пример:
 * <p>
 * Товары в заказе:
 * Ноутбук — 1000 × 1
 * Мышь — 50 × 2
 * Клавиатура — 120 × 1
 * <p>
 * Метод должен вывести:
 * Стоимость заказа: 1220"
 */

public class Main {
    public static void main(String[] args) {

        OrderItem laptop = new OrderItem("Ноутбук", 1000, 1);
        OrderItem mouse = new OrderItem("Мышь", 50, 2);
        OrderItem keyboard = new OrderItem("Клавиатура", 120, 1);

        Order orderNumberOne = new Order("ID-123-A");
        orderNumberOne.addItem(laptop);
        orderNumberOne.addItem(mouse);
        orderNumberOne.addItem(keyboard);

        orderNumberOne.calculateTotal();
    }
}