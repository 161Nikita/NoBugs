package homework_mock.task_oop.delivery;

public class DroneDelivery implements Delivery{
    private final String address;

    public DroneDelivery(String address) {
        this.address = address;
    }

    @Override
    public void deliver() {
        System.out.println("Дрон летит с посылкой на " + address);
    }
}
