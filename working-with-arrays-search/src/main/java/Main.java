import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 20;
        Random random = new Random();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(-100, 100);
        }

        System.out.println(Arrays.toString(numbers));

        System.out.printf("Максимальный элемент = %d%n", findMaxElement(numbers));
        System.out.printf("Индекс элемента со значением %d = %d%n", 50, findElementIndex(numbers, 50));
    }

    public static int findMaxElement(int[] numbers) {
        int max = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    public static int findElementIndex(int[] numbers, int target) {
        int index = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                index = i;
                break;
            }
        }
        return index;
    }
}
