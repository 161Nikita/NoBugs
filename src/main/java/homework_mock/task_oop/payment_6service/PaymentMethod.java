package homework_mock.task_oop.payment_6service;

public abstract class PaymentMethod {

    private final String owner;

    public PaymentMethod(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public abstract void pay(double amount);
}
