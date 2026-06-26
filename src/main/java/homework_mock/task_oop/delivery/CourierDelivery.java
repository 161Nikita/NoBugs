package homework_mock.task_oop.delivery;

public class CourierDelivery implements Delivery {
    private final String address;

    public CourierDelivery(String address) {
        this.address = address;
    }


    @Override
    public void deliver() {
        System.out.println("Курьер доставляет посылку на " + address);
    }
}
