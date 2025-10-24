import model.Student;

public class Main {
    public static void main(String[] args) {
        testTaskEncapsulationAndValidation();
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
}
