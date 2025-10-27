package senders;

public class SmsSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.printf("Отправили сообщение: '%s' через sms\n", message);
    }
}
