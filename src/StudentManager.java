package src;
import java.util.ArrayList;
public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("===== Student Records =====");

        for (Student student : students) {
            student.display();
        }
    }
}
