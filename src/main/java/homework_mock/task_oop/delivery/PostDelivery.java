package homework_mock.task_oop.delivery;

public class PostDelivery implements Delivery{
    private final String address;

    public PostDelivery(String address) {
        this.address = address;
    }

    @Override
    public void deliver() {
        System.out.println("Почта отправила посылку через сортировочный центр на " + address);
    }
}
