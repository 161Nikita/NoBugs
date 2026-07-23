package homework_mock.task_oop.payment_6service;

public class PayPalPayment extends PaymentMethod{
    public PayPalPayment(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("PayPal " + getOwner() + " оплатил " + (int) amount);
    }
}
