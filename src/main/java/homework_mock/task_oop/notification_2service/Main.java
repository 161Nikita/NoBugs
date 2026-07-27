package homework_mock.task_oop.notification_2service;

import java.util.ArrayList;
import java.util.List;

/**
 * "Система работает с разными типами уведомлений:
 *
 * Email уведомление
 * SMS уведомление
 * Push уведомление
 *
 * У каждого уведомления есть получатель и текст сообщения.
 *
 * Правила отправки:
 * Email — отправляется на email-адрес
 * SMS — отправляется на номер телефона
 * Push — отправляется на deviceId
 *
 * Нужно реализовать систему, которая может отправить уведомления всем получателям.
 *
 * Требование:
 * Метод, который отправляет уведомления, должен работать со списком уведомлений, не зная их конкретный тип.
 * Каждый тип уведомления должен сам реализовывать логику отправки.
 *
 * Метод должен вывести, например:
 * Email отправлен на alex@test.com: Ваш заказ готов
 * SMS отправлено на +123456789: Код подтверждения 1234
 * Push отправлено на device-777: У вас новое сообщение"
 */

public class Main {

    public static void main(String[] args) {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new EmailNotification("alex@test.com", "Ваш заказ готов"));
        notifications.add(new SmsNotification("+123456789", "Код подтверждения 1234"));
        notifications.add(new PushNotification("device-777", "У вас новое сообщение"));


        NotificationSender notificationSender = new NotificationSender();
        notificationSender.notificationAll(notifications);
    }



}
