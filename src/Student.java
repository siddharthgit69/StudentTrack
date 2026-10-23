package src;

public class Student{
    private String name;
    private String class_name;
    private int id;

    public Student(String name , String class_name , int id){
        this.name = name;
        this.class_name = class_name;
        this.id = id;
    }

    public String getstudentname(){
        return name;
    }
    public String getstudentclass(){
        return class_name;
    }
    public int getstudentid(){
        return id;
    }

    void display(){
        System.out.println("Studend ID: "+id);
        System.out.println("Student Name: "+name);
        System.out.println("Student Class: "+class_name);
    }
}

