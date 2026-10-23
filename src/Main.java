
package src;
import java.util.*;
public class Main {
    public static void main(String[] args) {
       ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Rahul" , "A2" , 3156));
        students.add(new Student("Aman" , "B1" , 2214));
        students.add(new Student("Pranav" , "B8" , 2256));

        int searchid = 2256;
        boolean found = false;

        for (Student student : students) {
            if (student.getstudentid() == searchid) {
                student.display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}
