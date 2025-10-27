import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> emails = List.of("example1@mail.ru", "example1@mail.ru", "example2@mail.ru", "example3@gmail.com",
                "example4@yandex.ru", "example5@yandex.ru");

        System.out.printf("Размер списка emails = %d\n", emails.size());

        Set<String> distinctEmails = new HashSet<>(emails);

        System.out.printf("Размер списка distinctEmails после удаления дубликатов = %d\n", distinctEmails.size());

        Map<String, Integer> domains = new HashMap<>();

        distinctEmails.forEach(email -> domains.merge(getDomain(email), 1, Integer::sum));

        domains.forEach((key, value) -> System.out.printf("Домен %s встречается %d раз\n", key, value));
    }

    public static String getDomain(String email) {
        int index = email.indexOf("@");

        if (index == -1) {
            throw new IllegalArgumentException("Неверный формат email");
        }

        return email.substring(index + 1);
    }
}
