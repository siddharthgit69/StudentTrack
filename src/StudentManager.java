package src;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.txt";

    public boolean addStudent(Student student) {

        if (student == null || student.getstudentid() <= 0) {
            return false;
        }

        if (student.getstudentname() == null
                || student.getstudentname().trim().isEmpty()
                || student.getstudentclass() == null
                || student.getstudentclass().trim().isEmpty()) {
            return false;
        }

        if (searchStudent(student.getstudentid()) != null) {
            return false;
        }

        students.add(student);
        saveStudents();
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student searchStudent(int id) {

        for (Student student : students) {
            if (student.getstudentid() == id) {
                return student;
            }
        }

        return null;
    }

    public boolean updateStudent(int id, String newName, String newClass) {

        Student student = searchStudent(id);

        if (student == null
                || newName == null || newName.trim().isEmpty()
                || newClass == null || newClass.trim().isEmpty()) {
            return false;
        }

        student.setstudentname(newName.trim());
        student.setstudentclass(newClass.trim());

        saveStudents();
        return true;
    }

    public boolean removeStudent(int id) {

        Student student = searchStudent(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        saveStudents();
        return true;
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

    public void saveStudents() {

        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {

            for (Student student : students) {

                writer.write(
                        student.getstudentid() + "|"
                        + student.getstudentname() + "|"
                        + student.getstudentclass()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void loadStudents() {

        Path file = Paths.get(FILE_NAME);

        students.clear();

        if (!Files.exists(file)) {

            students.add(new Student("Rahul", "A2", 3156));
            students.add(new Student("Aman", "B1", 2214));
            students.add(new Student("Pranav", "B8", 2256));

            saveStudents();

            System.out.println("Default student records created.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 3) {
                    continue;
                }

                try {

                    int id = Integer.parseInt(data[0]);

                    if (id <= 0 || data[1].trim().isEmpty()
                            || data[2].trim().isEmpty()
                            || searchStudent(id) != null) {
                        continue;
                    }

                    students.add(
                            new Student(data[1], data[2], id)
                    );

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid student record.");
                }
            }

            System.out.println("Student records loaded successfully!");

        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
}
