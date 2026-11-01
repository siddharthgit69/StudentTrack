package src;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Rahul", "A2", 3156));
        students.add(new Student("Aman", "B1", 2214));
        students.add(new Student("Pranav", "B8", 2256));

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Student Search System =====");
        System.out.print("Enter Student ID to search: ");

        int searchid = sc.nextInt();

        int count = 0;

        for (Student student : students) {

            if (student.getstudentid() == searchid) {

                System.out.println("\nStudent Found!");
                student.display();

                count++;
                break;
            }
        }

        if (count == 0) {
            System.out.println("\nStudent not found.");
        }

        sc.close();
    }
}