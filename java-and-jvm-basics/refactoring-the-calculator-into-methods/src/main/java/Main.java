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
            double a = numbers[0];
            double b = numbers[1];

            System.out.println("Введите операцию (+, -, *, /)");
            String operation = reader.readLine();


            double result;
            switch (operation) {
                case "+" -> result = add(a, b);
                case "-" -> result = subtract(a, b);
                case "*" -> result = multiply(a, b);
                case "/" -> {
                    if (b == 0) {
                        System.out.println("Недопустимое значение: деление на 0");
                        continue;
                    }
                    result = divide(a, b);
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

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }
}
