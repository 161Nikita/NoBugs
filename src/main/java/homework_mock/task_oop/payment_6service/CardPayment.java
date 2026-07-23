package homework_mock.task_oop.payment_6service;

public class CardPayment extends PaymentMethod{
    public CardPayment(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Карта " + getOwner() + " оплатила " + (int) amount);
    }
}
