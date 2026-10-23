
package src;
import java.util.*;
public class Main {
    public static void main(String[] args) {
       ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Rahul" , "A2" , 3156));
        students.add(new Student("Aman" , "B1" , 2214));
        students.add(new Student("Pranav" , "B8" , 2256));

        for (Student student : students) {
            student.display();
            System.out.println("----------------");

    }
}
}