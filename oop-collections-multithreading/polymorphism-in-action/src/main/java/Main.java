import senders.EmailSender;
import senders.PushSender;
import senders.SmsSender;
import service.SenderService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SenderService service = new SenderService(List.of(new SmsSender(), new EmailSender(), new PushSender()));

        service.send("Hello!");
    }
}
