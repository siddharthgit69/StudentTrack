package src;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        manager.addStudent(new Student("Rahul", "A2", 3156));
        manager.addStudent(new Student("Aman", "B1", 2214));
        manager.addStudent(new Student("Pranav", "B8", 2256));

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Display All Students");
            System.out.println("2. Search Student by ID");
            System.out.println("3. Add Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

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

                case 3:

                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student Class: ");
                    String className = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    manager.addStudent(
                        new Student(name, className, id)
                    );

                    break;

                case 4:

                    System.out.print("Enter Student ID to remove: ");
                    int removeId = sc.nextInt();

                    manager.removeStudent(removeId);

                    break;

                case 5:
                    System.out.println("Exiting Student Management System...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
