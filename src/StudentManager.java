
package src;

import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {

        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n===== Student Records =====");

        for (Student student : students) {
            student.display();
            System.out.println("--------------------");
        }
    }

    public Student searchStudent(int id) {

        for (Student student : students) {

            if (student.getstudentid() == id) {
                return student;
            }
        }

        return null;
    }
}
