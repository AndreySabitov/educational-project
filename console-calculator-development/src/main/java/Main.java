import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = "^-?\\d+(\\.\\d+)?\\s-?\\d+(\\.\\d+)?$";

        while (true) {
            System.out.println();
            System.out.println("Введите два числа в формате: 'A B'. Если хотите выйти, введите exit");
            String inputValue = reader.readLine();

            if (inputValue.equalsIgnoreCase("exit")) {
                break;
            }
            if (!inputValue.matches(pattern)) {
                System.out.println("Некорректный формат ввода данных");
                continue;
            }

            double[] numbers = Arrays.stream(inputValue.split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            System.out.println("Введите операцию (+, -, *, /)");
            String operation = reader.readLine();


            double result;
            switch (operation) {
                case "+" -> result = numbers[0] + numbers[1];
                case "-" -> result = numbers[0] - numbers[1];
                case "*" -> result = numbers[0] * numbers[1];
                case "/" -> {
                    if (numbers[1] == 0) {
                        System.out.println("Недопустимое значение: деление на 0");
                        continue;
                    }
                    result = numbers[0] / numbers[1];
                }
                default -> {
                    System.out.println("Некорректный ввод: введите +, -, * или /");
                    continue;
                }
            }

            System.out.println(result);
        }

        System.out.println("Программа завершена");
        reader.close();
    }
}
