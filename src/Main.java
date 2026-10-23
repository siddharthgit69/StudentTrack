
package src;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(
            "Java",
            "A2",
            3214
        );
        Student s2 = new Student("DAA","A2",124);

        s1.display();
        s2.display();
    }
}
