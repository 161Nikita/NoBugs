package homework_mock.task_oop.notification_2service;

import java.util.List;

public class NotificationSender {

    public void notificationAll(List<Notification> notifications) {

        for (Notification notification : notifications) {
            notification.sendNotification();
        }
    }
}
