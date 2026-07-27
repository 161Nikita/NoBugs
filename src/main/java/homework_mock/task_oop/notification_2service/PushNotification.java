package homework_mock.task_oop.notification_2service;

public class PushNotification implements Notification{
    private final String address;
    private final String message;

    public PushNotification(String address, String message) {
        this.address = address;
        this.message = message;
    }

    @Override
    public void sendNotification() {
        System.out.println("Push отправлено на " + address +  ": " + message);
    }
}
