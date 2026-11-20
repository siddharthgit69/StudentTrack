
package src;

import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class PerformanceManager {

    private final ArrayList<SubjectMarks> records;
    private final StudentManager studentManager;

    private final String FILE_NAME = "marks.txt";

    public PerformanceManager(StudentManager studentManager) {

        if (studentManager == null) {
            throw new IllegalArgumentException(
                    "StudentManager cannot be null."
            );
        }

        this.studentManager = studentManager;
        this.records = new ArrayList<>();
    }

    
    public boolean addMarks(
            int studentId, int python, int java, int daa, Integer elx) {

        if (studentManager.searchStudent(studentId) == null) {
            return false;
        }

        if (searchMarks(studentId) != null) {
            return false;
        }

        try {

            SubjectMarks marks = new SubjectMarks(
                    studentId, python, java, daa, elx
            );

            records.add(marks);
            saveMarks();

            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    
    public SubjectMarks searchMarks(int studentId) {

        for (SubjectMarks marks : records) {
            if (marks.getStudentId() == studentId) {
                return marks;
            }
        }

        return null;
    }

    
    public ArrayList<SubjectMarks> getAllRecords() {
        return new ArrayList<>(records);
    }

    
    public boolean removeMarks(int studentId) {

        SubjectMarks marks = searchMarks(studentId);

        if (marks == null) {
            return false;
        }

        records.remove(marks);
        saveMarks();

        return true;
    }

    
    public void saveMarks() {

        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {

            for (SubjectMarks marks : records) {

                writer.write(
                    marks.getStudentId() + "|" +
                    marks.getPython() + "|" +
                    marks.getJava() + "|" +
                    marks.getDaa() + "|" +
                    (marks.getElx() == null ? "" : marks.getElx())
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving marks: " + e.getMessage());
        }
    }

    
    public void loadMarks() {

        Path file = Paths.get(FILE_NAME);

        if (!Files.exists(file)) {
            System.out.println("No existing marks records found.");
            return;
        }

        records.clear();

        try (BufferedReader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {

                    String[] data = line.split("\\|", -1);

                    if (data.length != 5) continue;

                    int id = Integer.parseInt(data[0]);
                    int python = Integer.parseInt(data[1]);
                    int java = Integer.parseInt(data[2]);
                    int daa = Integer.parseInt(data[3]);

                    Integer elx = data[4].isEmpty()
                            ? null
                            : Integer.parseInt(data[4]);

                    if (studentManager.searchStudent(id) == null
                            || searchMarks(id) != null) {
                        continue;
                    }

                    records.add(new SubjectMarks(
                            id, python, java, daa, elx
                    ));

                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid marks record.");
                }
            }

            System.out.println("Marks records loaded successfully!");

        } catch (IOException e) {
            System.out.println("Error loading marks: " + e.getMessage());
        }
    }
}
