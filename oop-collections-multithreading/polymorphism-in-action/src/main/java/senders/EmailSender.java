package senders;

public class EmailSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.printf("Отправили сообщение: '%s' через email\n", message);
    }
}
