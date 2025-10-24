import model.Person;
import model.Student;
import model.Teacher;

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
        System.out.println();
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
        System.out.println();
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
        System.out.println();
    }

    public static void testInheritanceAndPolymorphism() {
        Person person = new Person();
        Student student = new Student();
        Teacher teacher = new Teacher();

        Person[] persons = new Person[] {person, student, teacher};

        for (Person p: persons) {
            System.out.println(p.introduce());
        }
        System.out.println();
    }
}
