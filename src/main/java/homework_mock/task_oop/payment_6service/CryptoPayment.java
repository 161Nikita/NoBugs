package homework_mock.task_oop.payment_6service;

public class CryptoPayment extends PaymentMethod{
    public CryptoPayment(String owner) {
        super(owner);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Криптокошелек " + getOwner() + " отправил " + (int) amount );
    }
}
