
package src;

public class Student {
    private String studentId;
    private String name;
    private String section;

    public Student(String studentId, String name, String section) {
        this.studentId = studentId;
        this.name = name;
        this.section = section;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Section: " + section);
    }
}
