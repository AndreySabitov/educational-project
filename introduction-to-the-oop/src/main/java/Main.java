import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Andy", 28, 7);
        Student student2 = new Student("Olga", 32, 9);
        Student student3 = new Student("Vasya", 25, 6);

        List<Student> students = List.of(student1, student2, student3);

        Student bestStudent = students.stream()
                .max(Comparator.comparing(Student::getAverageGrade)).get();

        System.out.println(bestStudent);
    }
}
