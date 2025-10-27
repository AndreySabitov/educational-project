import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        testCompletableFuture(mapper);

        testExceptionally(mapper);
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
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
