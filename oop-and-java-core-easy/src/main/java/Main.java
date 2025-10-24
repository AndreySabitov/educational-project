import manager.StudentManager;
import model.Person;
import model.Student;
import model.Teacher;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testTaskEncapsulationAndValidation();

        testTaskOverloadingConstructors();

        testEqualsAndHashCode();

        testWorkWithArrayListAndComparator();

        testInheritanceAndPolymorphism();

        testInterfacesAndAbstractClasses();

        testHashMap();

        testStreamApi();

        testExceptionHandling();
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
        System.out.println("-".repeat(100));
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
        System.out.println("-".repeat(100));
    }

    public static void testWorkWithArrayListAndComparator() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Andrey", 17, 5));
        students.add(new Student("Andrey", 18, 7));
        students.add(new Student("Petya", 19, 8));
        students.add(new Student("Vasya", 20, 7));
        students.add(new Student("Oleg", 21, 8));
        students.add(new Student("Masha", 18, 6));
        students.add(new Student("Boris", 19, 10));
        students.add(new Student("Boris", 19, 9));

        Comparator<Student> studentComparator = new Comparator<>() {
            @Override
            public int compare(Student o1, Student o2) {
                int compareByNameResult = String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
                if (compareByNameResult != 0) {
                    return compareByNameResult;
                } else {
                    return Integer.compare(o1.getAverageGrade(), o2.getAverageGrade()) * -1;
                }
            }
        };

        Collections.sort(students, studentComparator);

        System.out.println(students);
        System.out.println("-".repeat(100));
    }

    public static void testInheritanceAndPolymorphism() {
        Person person = new Person();
        Student student = new Student();
        Teacher teacher = new Teacher();

        Person[] persons = new Person[]{person, student, teacher};

        for (Person p : persons) {
            System.out.println(p.introduce());
        }
        System.out.println("-".repeat(100));
    }

    public static void testInterfacesAndAbstractClasses() {
        Shape circle = new Circle(1);
        Shape rectangle = new Rectangle(5, 5);

        List<Shape> shapes = List.of(circle, rectangle);

        double areasSum = shapes.stream()
                .mapToDouble(Shape::getArea)
                .sum();

        System.out.printf("Сумма площадей = %s%n", areasSum);
        System.out.println("-".repeat(100));
    }

    public static void testHashMap() {
        StudentManager manager = new StudentManager();
        Student student1 = new Student("Andy", 19, "id1", 8);
        Student student2 = new Student("Andy", 19, "id2", 8);
        Student student3 = new Student("Andy", 19, "id3", 8);
        Student student4 = new Student("Andy", 19, "id4", 8);
        Student student5 = new Student("Andy", 19, "id5", 8);
        Student student6 = new Student("Andy", 19, "id6", 8);

        manager.add(student1);
        manager.add(student2);
        manager.add(student3);
        manager.add(student4);
        manager.add(student5);
        manager.add(student6);

        List<Student> students = manager.findAll();
        long time1 = System.nanoTime();
        for (Student student : students) {
            if (student.getStudentId().equals("id5")) {
                break;
            }
        }
        long time2 = System.nanoTime();
        Student findedStudent = manager.findById("id5");
        long time3 = System.nanoTime();
        long arrayListTime = time2 - time1;
        long hashMapTime = time3 - time2;
        System.out.printf("Время поиска в List = %dнс%n", arrayListTime);
        System.out.printf("Время поиска в HashMap = %dнс%n", hashMapTime);
        System.out.printf("Время поиска в HashMap меньше, чем в ArrayList - %s%n", hashMapTime < arrayListTime);

        System.out.printf("Нашли нужного студента: %s%n", student1.equals(findedStudent));

        manager.deleteById("id1");

        try {
            manager.findById("id1");
        } catch (RuntimeException e) {
            System.out.println("Студент с id = id1 успешно удален");
        }
        System.out.println("-".repeat(100));
    }

    public static void testStreamApi() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Andrey", 21, 6));
        students.add(new Student("Andy", 18, 7));
        students.add(new Student("Alena", 22, 8));
        students.add(new Student("Vasya", 20, 7));

        double average = students.stream()
                .filter(student -> student.getName().startsWith("A") && student.getAge() > 20)
                .mapToInt(Student::getAverageGrade)
                .average().orElse(-1.0);

        System.out.printf("Средний балл всех студентов старше 20 лет с именами, которые начинаются на A = %s%n", average);
        System.out.println("-".repeat(100));
    }

    public static void testExceptionHandling() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            reader.readLine();
        } catch (IOException e) {
            System.out.println("Поймали FileNotFoundException или IOException");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Поймали IOException");
            }
        }
    }
}
