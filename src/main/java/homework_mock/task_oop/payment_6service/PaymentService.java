package homework_mock.task_oop.payment_6service;

import java.util.List;

public class PaymentService {
    public void payAll(List<PaymentMethod> paymentMethods, double amount) {
        for (PaymentMethod payment : paymentMethods) {
            payment.pay(amount);
        }
    }
}
