package homework_12.ocp;

public class CreditCardPayment implements PaymentMethod{
    @Override
    public void process(double amount) {
        System.out.println("Оплата кредитной картой на сумму " + amount);
    }
}
