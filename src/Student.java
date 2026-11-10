package src;
public class Student {
    private String name;
    private String class_name;
    private int id;

    public Student(String name, String class_name, int id) {
        this.name = name;
        this.class_name = class_name;
        this.id = id;
    }
    public String getstudentname() {
        return name;
    }
    public String getstudentclass() {
        return class_name;
    }
    public int getstudentid() {
        return id;
    }
    public void setstudentname(String name) {
        this.name = name;
    }
    public void setstudentclass(String class_name) {
        this.class_name = class_name;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Student Class: " + class_name);
    }
}
