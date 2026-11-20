
package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class AttendanceManager {

    private final ArrayList<AttendanceRecord> records;
    private final StudentManager studentManager;
    private final String FILE_NAME = "attendance.txt";

    public AttendanceManager(StudentManager studentManager) {
        if (studentManager == null) {
            throw new IllegalArgumentException(
                "StudentManager cannot be null."
            );
        }

        this.studentManager = studentManager;
        this.records = new ArrayList<>();
    }

    public boolean markAttendance(int studentId,
                                  LocalDate date,
                                  boolean present) {

        if (studentManager.searchStudent(studentId) == null) {
            System.out.println("Student ID not found.");
            return false;
        }

        if (date == null) {
            System.out.println("Invalid attendance date.");
            return false;
        }

        if (findRecord(studentId, date) != null) {
            System.out.println("Attendance already marked for this date.");
            return false;
        }

        records.add(new AttendanceRecord(studentId, date, present));
        saveAttendance();

        System.out.println("Attendance marked successfully.");
        return true;
    }

    public AttendanceRecord findRecord(int studentId, LocalDate date) {
        for (AttendanceRecord record : records) {
            if (record.getStudentId() == studentId
                    && record.getDate().equals(date)) {
                return record;
            }
        }
        return null;
    }

    public ArrayList<AttendanceRecord> getRecordsForStudent(int studentId) {
        ArrayList<AttendanceRecord> result = new ArrayList<>();

        for (AttendanceRecord record : records) {
            if (record.getStudentId() == studentId) {
                result.add(record);
            }
        }

        return result;
    }

    public int getPresentDays(int studentId) {
        int count = 0;

        for (AttendanceRecord record : records) {
            if (record.getStudentId() == studentId && record.isPresent()) {
                count++;
            }
        }

        return count;
    }

    public int getAbsentDays(int studentId) {
        int count = 0;

        for (AttendanceRecord record : records) {
            if (record.getStudentId() == studentId && !record.isPresent()) {
                count++;
            }
        }

        return count;
    }

    public int getTotalDays(int studentId) {
        return getPresentDays(studentId) + getAbsentDays(studentId);
    }

    public double getAttendancePercentage(int studentId) {
        int total = getTotalDays(studentId);

        if (total == 0) {
            return 0.0;
        }

        return getPresentDays(studentId) * 100.0 / total;
    }

    public void displayAttendance(int studentId) {
        if (studentManager.searchStudent(studentId) == null) {
            System.out.println("Student ID not found.");
            return;
        }

        ArrayList<AttendanceRecord> studentRecords =
                getRecordsForStudent(studentId);

        if (studentRecords.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        System.out.println("\n===== Attendance Records =====");
        System.out.printf("%-12s %-15s %-10s%n",
                "Student ID", "Date", "Status");

        for (AttendanceRecord record : studentRecords) {
            record.display();
        }

        System.out.println("--------------------------------");
        System.out.println("Present days: " + getPresentDays(studentId));
        System.out.println("Absent days: " + getAbsentDays(studentId));
        System.out.println("Total days: " + getTotalDays(studentId));

        System.out.printf("Attendance: %.2f%%%n",
                getAttendancePercentage(studentId));

        System.out.println("================================");
    }

    public void displayAllAttendance() {
        if (records.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        System.out.println("\n===== All Attendance Records =====");
        System.out.printf("%-12s %-15s %-10s%n",
                "Student ID", "Date", "Status");

        for (AttendanceRecord record : records) {
            record.display();
        }
    }

    public boolean removeStudentAttendance(int studentId) {
        boolean removed = records.removeIf(
                record -> record.getStudentId() == studentId
        );

        if (removed) {
            saveAttendance();
        }

        return removed;
    }

    public ArrayList<AttendanceRecord> getAllRecords() {
        return new ArrayList<>(records);
    }

    public void saveAttendance() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {

            for (AttendanceRecord record : records) {
                writer.write(
                    record.getStudentId() + "|" +
                    record.getDate() + "|" +
                    record.isPresent()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving attendance: " + e.getMessage());
        }
    }

    public void loadAttendance() {
        Path file = Paths.get(FILE_NAME);

        if (!Files.exists(file)) {
            System.out.println("No existing attendance records found.");
            return;
        }

        records.clear();

        try (BufferedReader reader = Files.newBufferedReader(
                file, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split("\\|", -1);

                    if (data.length != 3) {
                        continue;
                    }

                    int studentId = Integer.parseInt(data[0]);
                    LocalDate date = LocalDate.parse(data[1]);
                    boolean present = Boolean.parseBoolean(data[2]);

                    if (!data[2].equalsIgnoreCase("true")
                            && !data[2].equalsIgnoreCase("false")) {
                        continue;
                    }

                    if (studentManager.searchStudent(studentId) == null) {
                        continue;
                    }

                    if (findRecord(studentId, date) == null) {
                        records.add(
                            new AttendanceRecord(studentId, date, present)
                        );
                    }

                } catch (Exception e) {
                    System.out.println("Skipping invalid attendance record.");
                }
            }

            System.out.println("Attendance records loaded successfully!");

        } catch (IOException e) {
            System.out.println("Error loading attendance: " + e.getMessage());
        }
    }
}
