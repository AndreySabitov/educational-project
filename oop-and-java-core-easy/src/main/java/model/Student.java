package model;

import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private int averageGrade;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, int averageGrade) {
        this.name = name;
        this.age = age;
        if (averageGrade >= 0 && averageGrade <= 10) {
            this.averageGrade = averageGrade;
        } else {
            throw new IllegalArgumentException("Некорректное значение среднего балла");
        }
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getAverageGrade() {
        return averageGrade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAverageGrade(int averageGrade) {
        if (averageGrade < 0 || averageGrade > 10) {
            throw new IllegalArgumentException("Некорректное значение среднего балла");
        }

        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", averageGrade=" + averageGrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
