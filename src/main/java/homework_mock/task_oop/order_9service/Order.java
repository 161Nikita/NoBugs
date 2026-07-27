package homework_mock.task_oop.order_9service;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String orderId;
    private List<OrderItem> items;

    public Order(String orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {

            total += item.getTotal();
        }
        System.out.println("Стоимость заказа: " + (int) total);

    }
}