import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(-100, 100);
        }

        System.out.printf("Массив до сортировки: %s%n", Arrays.toString(numbers));
        bubbleSort(numbers);
        System.out.printf("Массив после сортировки: %s%n", Arrays.toString(numbers));
    }

    public static void bubbleSort(int[] numbers) {
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    isSorted = false;
                }
            }
        }
    }
}
