package homework_mock.task_oop.deleviry_4service;

public class CourierDelivery extends Delivery{
    public CourierDelivery(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Курьер доставляет посылку на " + getAddress());
    }
}
