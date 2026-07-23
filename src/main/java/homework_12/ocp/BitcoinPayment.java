package homework_12.ocp;

public class BitcoinPayment implements PaymentMethod{
    @Override
    public void process(double amount) {
        System.out.println("Оплата Bitcoin на сумму " + amount);
    }
}
