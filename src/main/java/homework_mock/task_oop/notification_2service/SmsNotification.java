package homework_mock.task_oop.notification_2service;

public class SmsNotification implements Notification{
    private final String address;
    private final String message;

    public SmsNotification(String address, String message) {
        this.address = address;
        this.message = message;
    }

    @Override
    public void sendNotification() {
        System.out.println("SMS отправлено на " + address +  ": " + message);
    }
}
