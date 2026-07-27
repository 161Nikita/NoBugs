package homework_mock.task_oop.status_14service;

/*
"В системе есть **заказ**.

У заказа есть: Order
* номер заказа
* текущее состояние

Заказ может находиться в одном из состояний: OrderStatus
* `CREATED`
* `PAID`
* `SHIPPED`
* `DELIVERED`
* `CANCELLED`
Нужно реализовать систему, которая управляет состоянием заказа.

Доступные действия:
* оплатить заказ
* отправить заказ
* доставить заказ
* отменить заказ

Правила переходов:
CREATED → PAID
PAID → SHIPPED
SHIPPED → DELIVERED
CREATED → CANCELLED
PAID → CANCELLED

Нельзя:

* отправить неоплаченный заказ
* доставить неотправленный заказ
* изменить состояние после `DELIVERED` или `CANCELLED`
# Пример работы

Создаем заказ:
Order 1001 создан

Оплата:
Order 1001 оплачен
Отправка:
Order 1001 отправлен
Доставка:
Order 1001 доставлен

Если попытаться отправить неоплаченный заказ:
Ошибка: заказ должен быть оплачен
"
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Создаем заказ:");
        Order order = new Order(1001);
        order.pay();
        order.ship();
        order.deliver();
    }
}
