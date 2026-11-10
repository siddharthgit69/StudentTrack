package src;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";
    public void addStudent(Student student) {

        if (searchStudent(student.getstudentid()) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        students.add(student);
        System.out.println("Student added successfully!");

        saveStudents();
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

    public void removeStudent(int id) {

        Student student = searchStudent(id);

        if (student != null) {

            students.remove(student);

            System.out.println("Student removed successfully!");

            saveStudents();

        } else {

            System.out.println("Student not found!");
        }
    }

    public void updateStudent(int id, String newName, String newClass) {

        Student student = searchStudent(id);

        if (student != null) {

            student.setstudentname(newName);
            student.setstudentclass(newClass);

            System.out.println("Student details updated successfully!");

            saveStudents();

        } else {

            System.out.println("Student not found!");
        }
    }

    public void saveStudents() {

        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {

            for (Student student : students) {

                writer.write(
                    student.getstudentid() + "|" +
                    student.getstudentname() + "|" +
                    student.getstudentclass()
                );

                writer.newLine();
            }

            System.out.println("Student records saved.");

        } catch (IOException e) {

            System.out.println("Error saving student records: " + e.getMessage());
        }
    }

    public void loadStudents() {

        Path file = Paths.get(FILE_NAME);

        if (!Files.exists(file)) {

            System.out.println("No existing records found.");
            System.out.println("Loading default student records...");

            students.add(new Student("Rahul", "A2", 3156));
            students.add(new Student("Aman", "B1", 2214));
            students.add(new Student("Pranav", "B8", 2256));

            saveStudents();

            return;
        }

        students.clear();

        try (BufferedReader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 3) {
                    System.out.println("Skipping invalid student record.");
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);

                    if (searchStudent(id) != null) {
                        System.out.println("Skipping duplicate student ID: " + id);
                        continue;
                    }

                    students.add(
                        new Student(data[1], data[2], id)
                    );

                } catch (NumberFormatException e) {

                    System.out.println("Skipping invalid student ID.");
                }
            }

            System.out.println("Student records loaded successfully!");

        } catch (IOException e) {

            System.out.println("Error loading student records: " + e.getMessage());
        }
    }
}
