
package src;

public class SubjectMarks {

    private final int studentId;
    private final int python;
    private final int java;
    private final int daa;
    private final Integer elx;

    public SubjectMarks(
            int studentId, int python, int java, int daa, Integer elx) {

        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }

        validateMark("Python", python);
        validateMark("Java", java);
        validateMark("DAA", daa);

        if (elx != null) {
            validateMark("ELX", elx);
        }

        this.studentId = studentId;
        this.python = python;
        this.java = java;
        this.daa = daa;
        this.elx = elx;
    }

    private void validateMark(String subject, int mark) {

        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException(
                    subject + " marks must be between 0 and 100."
            );
        }
    }

    public int getStudentId() {
        return studentId;
    }

    public int getPython() {
        return python;
    }

    public int getJava() {
        return java;
    }

    public int getDaa() {
        return daa;
    }

    public Integer getElx() {
        return elx;
    }

    public int getTotal() {
        return python + java + daa + (elx == null ? 0 : elx);
    }

    public int getMaximumMarks() {
        return elx == null ? 300 : 400;
    }

    public double getPercentage() {
        return getTotal() * 100.0 / getMaximumMarks();
    }

    public String getGrade() {

        double percentage = getPercentage();

        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";

        return "F";
    }

    public void display() {

        System.out.println("\n===== Academic Performance =====");
        System.out.println("Student ID: " + studentId);
        System.out.println("Python: " + python + "/100");
        System.out.println("Java: " + java + "/100");
        System.out.println("DAA: " + daa + "/100");

        System.out.println(
                "ELX: " + (elx == null ? "Not included" : elx + "/100")
        );

        System.out.println("--------------------------------");
        System.out.println("Total: " + getTotal() + "/" + getMaximumMarks());
        System.out.printf("Percentage: %.2f%%%n", getPercentage());
        System.out.println("Grade: " + getGrade());
        System.out.println("================================");
    }
}
