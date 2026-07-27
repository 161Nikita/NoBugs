package homework_mock.task_oop.payment_6service;

import java.util.ArrayList;
import java.util.List;

/**
 * "Система работает с разными способами оплаты:
 *
 * * банковская карта
 * * PayPal
 * * криптокошелек
 *
 * У каждого способа оплаты есть **владелец**.
 *
 * Все способы оплаты умеют:
 *
 * * оплачивать сумму
 *
 * Но каждый делает это по-своему:
 *
 * * банковская карта списывает деньги с карты
 * * PayPal проводит оплату через аккаунт
 * * криптокошелек отправляет перевод
 *
 * Нужно реализовать систему, которая может **выполнить все платежи**.
 *
 * Требование:
 *
 * Метод, который запускает оплату, должен работать **со списком способов оплаты**, не зная их конкретный тип.
 * Каждый тип оплаты должен **сам реализовывать**, как происходит оплата.
 *
 * Метод должен вывести, например:
 *
 * Карта Alex оплатила 1200
 * PayPal Maria оплатил 850
 * Криптокошелек Ivan отправил 5000
 * "
 */

public class Main {
    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        List<PaymentMethod> alexCard = new ArrayList<>();
        alexCard.add(new CardPayment("Alex"));
        paymentService.payAll(alexCard, 1200);

        List<PaymentMethod> payPalMaria = new ArrayList<>();
        payPalMaria.add(new PayPalPayment("Maria"));
        paymentService.payAll(payPalMaria, 850);

        List<PaymentMethod> cryptoIvan = new ArrayList<>();
        cryptoIvan.add(new CryptoPayment("Ivan"));
        paymentService.payAll(cryptoIvan, 5000);

    }
}
