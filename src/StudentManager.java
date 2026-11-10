
package src;

import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    // Add Student
    public void addStudent(Student student) {

        if (searchStudent(student.getstudentid()) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        students.add(student);
        System.out.println("Student added successfully!");
    }

    // Display All Students
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

    // Search Student
    public Student searchStudent(int id) {

        for (Student student : students) {

            if (student.getstudentid() == id) {
                return student;
            }
        }

        return null;
    }

    // Remove Student
    public void removeStudent(int id) {

        Student student = searchStudent(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
}
