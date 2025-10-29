package service;

import senders.NotificationSender;

import java.util.List;

public class SenderService {
    private final List<NotificationSender> senderList;

    public SenderService(List<NotificationSender> senderList) {
        this.senderList = senderList;
    }

    public void send(String message) {
        senderList.forEach(sender -> sender.send(message));
    }
}
