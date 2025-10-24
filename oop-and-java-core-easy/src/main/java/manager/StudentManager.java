package manager;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {
    private final Map<String, Student> students = new HashMap<>();

    public void add(Student student) {
        if (student.getStudentId() == null || student.getStudentId().isBlank()) {
            throw new RuntimeException("У студента должен быть задан id");
        }

        String studentId = student.getStudentId();
        if (students.containsKey(studentId)) {
            throw new RuntimeException("Студент с id = %s уже добавлен".formatted(studentId));
        }

        students.put(studentId, student);
    }

    public Student findById(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new RuntimeException("Не задан studentId");
        }

        if (!students.containsKey(studentId)) {
            throw new RuntimeException("Студент с id = %s не найден".formatted(studentId));
        }

        return students.get(studentId);
    }

    public void deleteById(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new RuntimeException("Не задан studentId");
        }

        students.remove(studentId);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}
