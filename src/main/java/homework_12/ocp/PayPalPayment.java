package homework_12.ocp;

public class PayPalPayment implements PaymentMethod{
    @Override
    public void process(double amount) {
        System.out.println("Оплата через PayPal на сумму " + amount);
    }
}
