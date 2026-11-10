
package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        manager.addStudent(new Student("Rahul", "A2", 3156));
        manager.addStudent(new Student("Aman", "B1", 2214));
        manager.addStudent(new Student("Pranav", "B8", 2256));

        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== Student Search System =====");
        System.out.print("Enter Student ID to search: ");

        int searchId = sc.nextInt();

        Student result = manager.searchStudent(searchId);

        if (result != null) {
            System.out.println("\nStudent Found!");
            result.display();
        } else {
            System.out.println("\nStudent not found.");
        }

        sc.close();
    }
}
