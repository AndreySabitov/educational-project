import currency.Currency;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Currency currency1 = new Currency("USD", "Dollar");
        Currency currency2 = new Currency("USD", "US Dollar");

        if (currency1.equals(currency2)) {
            System.out.println("Сравнение работает корректно");
        } else {
            System.out.println("Ошибка при сравнении объектов currency1 и currency2");
        }

        Set<Currency> currencies = new HashSet<>();
        currencies.add(currency1);
        if (!currencies.add(currency2)) {
            System.out.println("Объект currency2 не добавлен, так как эквивалентен currency1");
        }

        System.out.printf("set содержит объект currency1 = %s\n", currencies.contains(currency1));
    }
}
