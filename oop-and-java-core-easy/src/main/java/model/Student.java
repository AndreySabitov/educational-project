package model;

public class Student extends Person {
    private String studentId;
    private int averageGrade;

    public Student() {
    }

    public Student(String name, int age) {
        super(name, age);
    }

    public Student(String name, int age, int averageGrade) {
        super(name, age);
        if (averageGrade >= 0 && averageGrade <= 10) {
            this.averageGrade = averageGrade;
        } else {
            throw new IllegalArgumentException("Некорректное значение среднего балла");
        }
    }

    public Student(String name, int age, String studentId, int averageGrade) {
        super(name, age);
        this.studentId = studentId;
        this.averageGrade = averageGrade;
    }

    @Override
    public String introduce() {
        return "Hello from Student";
    }

    public int getAverageGrade() {
        return averageGrade;
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
                "}";
    }

    public String getStudentId() {
        return studentId;
    }
}
