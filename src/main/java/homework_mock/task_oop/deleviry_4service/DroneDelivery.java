package homework_mock.task_oop.deleviry_4service;

public class DroneDelivery extends Delivery{
    public DroneDelivery(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Дрон летит с посылкой на " + getAddress());
    }
}
