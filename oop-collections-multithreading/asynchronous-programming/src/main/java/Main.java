import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DashboardDto;
import model.Student;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        testCompletableFuture(mapper);

        testExceptionally(mapper);

        testThenCombine();
    }

    public static void testCompletableFuture(ObjectMapper mapper) {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                        return mapper.writeValueAsString(new Student("Roy", 18));
                    } catch (InterruptedException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).thenApply(str -> {
                    System.out.printf("Получили объект в формате JSON: %s%n", str);
                    try {
                        return mapper.readValue(str, Student.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).thenAccept(student -> System.out.printf("Получили объект класса Student: %s%n", student))
                .join();

        System.out.println("-".repeat(50));
    }

    public static void testExceptionally(ObjectMapper mapper) {
        Random random = new Random();

        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                        int n = random.nextInt(501, 1000);
                        if (n > 500) {
                            throw new RuntimeException("Намеренное исключение");
                        }
                        return mapper.writeValueAsString(new Student("Roy", 18));
                    } catch (InterruptedException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).exceptionally(throwable -> {
                    System.err.println("Обработано исключение: " + throwable.getMessage());
                    return "{}";
                }).thenApply(str -> {
                    System.out.printf("Получили объект в формате JSON: %s%n", str);
                    try {
                        return mapper.readValue(str, Student.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).thenAccept(student -> System.out.printf("Получили объект класса Student: %s%n", student))
                .join();

        System.out.println("-".repeat(50));
    }

    public static void testThenCombine() {
        CompletableFuture<String> futureProfile = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "Profile";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<List<String>> futureOrders = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                return List.of("order1", "order2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        long time1 = System.nanoTime();
        DashboardDto dto = futureProfile.thenCombine(futureOrders, DashboardDto::new).join();
        long time2 = System.nanoTime();
        System.out.printf("Получили результат %s за время %s%n", dto, Duration.ofNanos(time2 - time1).toMillis());
    }
}

