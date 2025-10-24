import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> vowels = Set.of('а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я');

        String text = reader.readLine().toLowerCase();
        int vowelsCount = 0;
        int consonantsCount = 0;
        char[] chars = text.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                builder.append(c);
            }
            if (vowels.contains(c)) {
                vowelsCount++;
            }
            if (!vowels.contains(c) && Character.isLetter(c)) {
                consonantsCount++;
            }
        }

        String[] words = builder.toString().split(" ");
        int wordsCount = words.length;

        System.out.printf("Слов в тексте: %d%n", wordsCount);
        System.out.printf("Гласных букв: %d%n", vowelsCount);
        System.out.printf("Согласных букв: %d%n", consonantsCount);

        reader.close();
    }
}
