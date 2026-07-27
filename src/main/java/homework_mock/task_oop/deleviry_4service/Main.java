package homework_mock.task_oop.deleviry_4service;

import java.util.ArrayList;
import java.util.List;

/**
 * "Система работает с разными способами доставки:
 *
 * * курьер
 * * дрон
 * * почта
 *
 * У каждой доставки есть **адрес получателя**.
 *
 * Все доставки умеют:
 *
 * * доставлять посылку
 *
 * Но каждая делает это по-своему:
 *
 * * курьер везет посылку на машине
 * * дрон летит к адресу
 * * почта отправляет посылку через сортировочный центр
 *
 * Нужно реализовать систему, которая может **отправить все доставки**.
 *
 * Требование:
 *
 * Метод, который запускает доставку, должен работать **со списком доставок**, не зная их конкретный тип.
 * Каждый тип доставки должен **сам реализовывать**, как происходит доставка.
 *
 * Метод должен вывести, например:
 *
 * Курьер доставляет посылку на ул. Ленина
 * Дрон летит с посылкой на ул. Пушкина
 * Почта отправила посылку через сортировочный центр на ул. Гагарина"
 */

public class Main {
    public static void main(String[] args) {
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(new CourierDelivery("ул. Ленина"));
        deliveries.add(new DroneDelivery("ул. Пушкина"));
        deliveries.add(new PostDelivery("ул. Гагарина"));

        DeliveryService deliveryService = new DeliveryService();

        deliveryService.sendAll(deliveries);
    }
}
