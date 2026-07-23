package homework_mock.task_oop.deleviry_4service;

public class PostDelivery extends Delivery{
    public PostDelivery(String address) {
        super(address);
    }

    @Override
    public void deliver() {
        System.out.println("Почта отправила посылку через сортировочный центр на " + getAddress());
    }
}
