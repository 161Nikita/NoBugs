package homework_mock.task_oop.deleviry_4service;

import java.util.List;

public class DeliveryService {
    public void sendAll(List<Delivery> deliveries) {

        for (Delivery delivery : deliveries) {
            delivery.deliver();
        }
    }
}
