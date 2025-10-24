import model.Student;

public class Main {
    public static void main(String[] args) {
        testTaskEncapsulationAndValidation();

        testTaskOverloadingConstructors();

        testEqualsAndHashCode();
    }

    public static void testTaskEncapsulationAndValidation() {
        Student student = new Student();

        try {
            student.setAverageGrade(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Выброшено исключение при отрицательном значении для averageGrade");
        }

        try {
            student.setAverageGrade(11);
        } catch (IllegalArgumentException e) {
            System.out.println("Выброшено исключение при значении для averageGrade больше 10");
        }
    }

    public static void testTaskOverloadingConstructors() {
        Student student = new Student();

        Student student1 = new Student("Andy", 19);

        Student student2 = new Student("Oleg", 18, 7);
    }

    public static void testEqualsAndHashCode() {
        Student student1 = new Student("Andrey", 18, 7);
        Student student2 = new Student("Olga", 19, 8);
        Student student3 = new Student("Andrey", 18, 7);

        if (student1.equals(student3)) {
            System.out.printf("Студент %s эквивалентен студенту %s%n", student1, student3);
        } else {
            System.out.printf("Ошибка: %s должен быть эквивалентен %s%n", student1, student3);
        }

        if (!student1.equals(student2)) {
            System.out.printf("Студент %s не эквивалентен студенту %s%n", student1, student2);
        } else {
            System.out.printf("Ошибка: %s не должен быть эквивалентен %s%n", student1, student2);
        }
    }
}
