package src;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        manager.addStudent(new Student("Rahul", "A2", 3156));
        manager.addStudent(new Student("Aman", "B1", 2214));
        manager.addStudent(new Student("Pranav", "B8", 2256));

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Display All Students");
        System.out.println("2. Search Student by ID");

        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                manager.displayStudents();
                break;

            case 2:
                System.out.print("Enter Student ID: ");
                int searchId = sc.nextInt();

                Student result = manager.searchStudent(searchId);

                if (result != null) {
                    System.out.println("\nStudent Found!");
                    result.display();
                } else {
                    System.out.println("\nStudent not found.");
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
