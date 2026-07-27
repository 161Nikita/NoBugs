package homework_mock.task_oop.status_14service;

public class Order {

    private final int orderId;
    private OrderStatus status;

    public Order(int orderId) {
        this.orderId = orderId;
        this.status = OrderStatus.CREATED;
        System.out.println("Order " + orderId + " создан");
    }

    // оплатить заказ CREATED → PAID
    public void pay() {

        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            System.out.println("Ошибка: нельзя изменить состояние после DELIVERED или CANCELLED");
            return;
        }
        if (status != OrderStatus.CREATED) {
            System.out.println("Ошибка: заказ нельзя оплатить повторно");
            return;
        }
        this.status = OrderStatus.PAID;
        System.out.println("\nОплата:\n" + "Order " + orderId + " оплачен");
    }

    // отправка заказа PAID → SHIPPED
    public void ship() {
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            System.out.println("Ошибка: нельзя изменить состояние после DELIVERED или CANCELLED");
            return;
        }
        if (status == OrderStatus.CREATED) {
            System.out.println("Ошибка: нельзя отправить неоплаченный заказ");
            return;
        }
        if (status != OrderStatus.PAID) {
            System.out.println("Ошибка: заказ уже отправлен");
            return;
        }
        this.status = OrderStatus.SHIPPED;
        System.out.println("Отправка:\n" + "Order " + orderId + " отправлен");
    }

    // доставка заказа SHIPPED → DELIVERED
    public void deliver() {
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            System.out.println("Ошибка: нельзя изменить состояние после DELIVERED или CANCELLED");
            return;
        }
        if (status != OrderStatus.SHIPPED) {
            System.out.println("Ошибка: нельзя доставить неотправленный заказ");
            return;
        }
        this.status = OrderStatus.DELIVERED;
        System.out.println("Доставка:\n" + "Order " + orderId + " доставлен");
    }
    // отмена заказа CANCELLED
    public void cancel() {
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            System.out.println("Ошибка: нельзя изменить состояние после DELIVERED или CANCELLED");
            return;
        }
        if (status == OrderStatus.SHIPPED) {
            System.out.println("Ошибка: нельзя отменить заказ после отправки");
            return;
        }
        this.status = OrderStatus.CANCELLED;
        System.out.println("Order " + orderId + " отменен");
    }
}
