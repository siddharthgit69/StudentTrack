package src;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        manager.loadStudents();
        
        int choice;
        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Display All Students");
            System.out.println("2. Search Student by ID");
            System.out.println("3. Add Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Update Student Details");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
        
            try {
                choice = Integer.parseInt(sc.nextLine());
        
            }
             catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
                continue;
            }
            switch (choice) {
                case 1:
                    manager.displayStudents();
                    break;
                
                    case 2:
                    System.out.print("Enter Student ID: ");
                    int searchId = Integer.parseInt(sc.nextLine());
                    Student result = manager.searchStudent(searchId);
                    if (result != null) {
                        System.out.println("\nStudent Found!");
                        result.display();
                    } else {
                        System.out.println("\nStudent not found.");
                    }
                    break;
                
                    case 3:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Student Class: ");
                    String className = sc.nextLine();
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    manager.addStudent(
                        new Student(name, className, id)
                    );
                    break;
                
                    case 4:
                    System.out.print("Enter Student ID to remove: ");
                    int removeId = Integer.parseInt(sc.nextLine());
                    manager.removeStudent(removeId);
                    break;
                    
                    case 5:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = Integer.parseInt(sc.nextLine());
                    Student existing = manager.searchStudent(updateId);
                    
                    if (existing != null) {
                        System.out.println("\nCurrent Student Details:");
                        existing.display();
                        System.out.print("\nEnter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Class: ");
                        String newClass = sc.nextLine();
                        manager.updateStudent(
                            updateId, newName, newClass
                        );
                    }
                    else {
                        System.out.println("Student not found!");
                    }
                    break;
                
                    case 6:
                    System.out.println("Exiting Student Management System...");
                    break;
                
                    default:
                    System.out.println("Invalid choice. Try again.");
            }
        } 
        while (choice != 6);
        sc.close();
    }
}
