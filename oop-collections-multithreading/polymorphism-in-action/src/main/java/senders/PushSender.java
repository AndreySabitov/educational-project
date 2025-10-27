package senders;

public class PushSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.printf("Отправили сообщение: '%s' через push\n", message);
    }
}
