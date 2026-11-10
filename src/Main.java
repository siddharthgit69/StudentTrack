
package src;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        manager.addStudent(new Student("Rahul", "A2", 3156));
        manager.addStudent(new Student("Aman", "B1", 2214));
        manager.addStudent(new Student("Pranav", "B8", 2256));

        manager.displayStudents();
    }
}
