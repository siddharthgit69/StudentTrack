
package src;

import java.time.LocalDate;

public class AttendanceRecord {
    private final int studentId;
    private final LocalDate date;
    private final boolean present;

    public AttendanceRecord(int studentId, LocalDate date, boolean present) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }

        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        this.studentId = studentId;
        this.date = date;
        this.present = present;
    }

    public int getStudentId() {
        return studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }

    public String getStatus() {
        return present ? "Present" : "Absent";
    }

    public void display() {
        System.out.printf("%-12d %-15s %-10s%n",
                studentId, date, getStatus());
    }
}
