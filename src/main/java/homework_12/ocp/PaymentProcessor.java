package homework_12.ocp;

/*
Нарушение OCP (Open/Closed Principle) – закрытый для расширения код
Задача: Избавьтесь от if-else, применив полиморфизм (наследование или интерфейсы).
 */

public class PaymentProcessor {

    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.process(amount);
    }

    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        PaymentMethod bitcoin = new BitcoinPayment();
        PaymentMethod card = new CreditCardPayment();
        PaymentMethod paypal = new PayPalPayment();
        paymentProcessor.processPayment(bitcoin, 200);
        paymentProcessor.processPayment(card, 300);
        paymentProcessor.processPayment(paypal, 800);
    }
}
