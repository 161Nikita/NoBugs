package homework_mock.task_oop.deleviry_4service;

public abstract class Delivery {

    private final String address;

    public Delivery(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public abstract void deliver();

}
