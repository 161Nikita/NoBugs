package homework_mock.task_oop.notification_2service;

public class EmailNotification implements Notification{

    private final String address;
    private final String message;

    public EmailNotification(String address, String message) {
        this.address = address;
        this.message = message;
    }

    @Override
    public void sendNotification() {
        System.out.println("Email отправлен на: " + address +  ": " + message);
    }
}
