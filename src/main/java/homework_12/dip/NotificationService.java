package homework_12.dip;

/*
Нарушение DIP (Dependency Inversion Principle) – жесткая зависимость от конкретных классов
Задача: Используйте интерфейсы и внедрение зависимостей, чтобы ослабить связь между классами.
 */

public class NotificationService {
    private final MessageSender messageSender;

    public NotificationService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendNotification(String message) {
        messageSender.sendMessage(message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        NotificationService notificationService = new NotificationService(email);
        notificationService.sendNotification("по электронной почте!");
    }
}
