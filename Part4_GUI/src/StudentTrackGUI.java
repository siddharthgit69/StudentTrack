
package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentTrackGUI extends JFrame {


    private static final Color FOREST = new Color(38, 61, 50);
    private static final Color FOREST_LIGHT = new Color(52, 79, 66);
    private static final Color BEIGE = new Color(237, 229, 214);
    private static final Color CREAM = new Color(247, 243, 234);
    private static final Color WALNUT = new Color(112, 75, 53);
    private static final Color SAGE = new Color(168, 181, 160);
    private static final Color AMBER = new Color(194, 139, 69);
    private static final Color TEXT = new Color(45, 48, 42);
    private static final Color MUTED = new Color(113, 109, 98);
    private static final Color BORDER = new Color(217, 208, 191);

    private StudentManager studentManager;
    private AttendanceManager attendanceManager;
    private PerformanceManager performanceManager;

    private JPanel contentPanel;
    private JLabel pageTitle;
    private JTable studentTable;
    private JTable attendanceTable;
    private JTable marksTable;

    public StudentTrackGUI() {

        setTitle("StudentTrack | Student Management System");
        setSize(1150, 720);
        setMinimumSize(new Dimension(950, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        studentManager = new StudentManager();
        studentManager.loadStudents();

        attendanceManager = new AttendanceManager(studentManager);
        attendanceManager.loadAttendance();

        performanceManager = new PerformanceManager(studentManager);
        performanceManager.loadMarks();

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BEIGE);

        root.add(createSidebar(), BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BEIGE);
        contentPanel.setBorder(new EmptyBorder(28, 30, 28, 30));

        root.add(contentPanel, BorderLayout.CENTER);

        setContentPane(root);

        showDashboard();
    }

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(235, 0));
        sidebar.setBackground(FOREST);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(30, 18, 25, 18));

        JLabel logo = new JLabel("StudentTrack");
        logo.setFont(new Font("Serif", Font.BOLD, 27));
        logo.setForeground(CREAM);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("STUDENT MANAGEMENT");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 9));
        subtitle.setForeground(SAGE);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(subtitle);
        sidebar.add(Box.createVerticalStrut(45));

        addSidebarButton(sidebar, "Dashboard", this::showDashboard);
        addSidebarButton(sidebar, "Students", this::showStudents);
        addSidebarButton(sidebar, "Attendance", this::showAttendance);
        addSidebarButton(sidebar, "Marks & Performance", this::showMarks);

        sidebar.add(Box.createVerticalGlue());

        JLabel footer = new JLabel("STUDENTTRACK  •  2026");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 10));
        footer.setForeground(SAGE);
        footer.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(footer);

        return sidebar;
    }

    private void addSidebarButton(JPanel sidebar, String text, Runnable action) {

        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setForeground(CREAM);
        button.setBackground(FOREST);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        button.setBorder(new EmptyBorder(12, 14, 12, 10));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> action.run());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(FOREST_LIGHT);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(FOREST);
            }
        });

        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(7));
    }

    
    private JPanel createHeader(String title, String description) {

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Serif", Font.BOLD, 32));
        heading.setForeground(WALNUT);

        JLabel subheading = new JLabel(description);
        subheading.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subheading.setForeground(MUTED);

        header.add(heading);
        header.add(Box.createVerticalStrut(6));
        header.add(subheading);
        header.add(Box.createVerticalStrut(25));

        return header;
    }

    
    private void setPage(String title, String description) {

        contentPanel.removeAll();

        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(BEIGE);

        page.add(createHeader(title, description), BorderLayout.NORTH);

        contentPanel.add(page, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

     
    private void showDashboard() {

        setPage("Welcome to StudentTrack",
                "A simple overview of your student records and academic activity.");

        JPanel dashboard = new JPanel(new GridLayout(1, 3, 18, 18));
        dashboard.setOpaque(false);
        dashboard.setBorder(new EmptyBorder(10, 0, 0, 0));

        dashboard.add(createCard(
                "TOTAL STUDENTS",
                String.valueOf(studentManager.getAllStudents().size()),
                "Registered students",
                FOREST
        ));

        dashboard.add(createCard(
                "ATTENDANCE RECORDS",
                String.valueOf(attendanceManager.getAllRecords().size()),
                "Records marked",
                WALNUT
        ));

        dashboard.add(createCard(
                "MARKS RECORDS",
                String.valueOf(performanceManager.getAllRecords().size()),
                "Academic records",
                AMBER
        ));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(dashboard, BorderLayout.NORTH);

        JPanel note = new JPanel(new BorderLayout());
        note.setBackground(CREAM);
        note.setBorder(new EmptyBorder(22, 24, 22, 24));

        JLabel noteTitle = new JLabel("Your academic workspace");
        noteTitle.setFont(new Font("Serif", Font.BOLD, 21));
        noteTitle.setForeground(WALNUT);

        JLabel noteText = new JLabel(
                "<html>Manage student information, record daily attendance, "
                + "and keep track of academic performance<br>"
                + "from one simple application.</html>"
        );
        noteText.setFont(new Font("SansSerif", Font.PLAIN, 13));
        noteText.setForeground(MUTED);

        JPanel noteContent = new JPanel();
        noteContent.setOpaque(false);
        noteContent.setLayout(new BoxLayout(noteContent, BoxLayout.Y_AXIS));
        noteContent.add(noteTitle);
        noteContent.add(Box.createVerticalStrut(10));
        noteContent.add(noteText);

        note.add(noteContent, BorderLayout.CENTER);

        wrapper.add(note, BorderLayout.CENTER);

        contentPanel.add(wrapper, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createCard(String title, String value,
                              String description, Color accent) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CREAM);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, accent),
                new EmptyBorder(22, 20, 22, 20)
        ));

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("SansSerif", Font.BOLD, 11));
        cardTitle.setForeground(MUTED);

        JLabel cardValue = new JLabel(value);
        cardValue.setFont(new Font("Serif", Font.BOLD, 39));
        cardValue.setForeground(accent);

        JLabel cardDescription = new JLabel(description);
        cardDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cardDescription.setForeground(MUTED);

        card.add(cardTitle);
        card.add(Box.createVerticalStrut(16));
        card.add(cardValue);
        card.add(Box.createVerticalStrut(8));
        card.add(cardDescription);

        return card;
    }

     
    private JTable createTable(String[] columns) {

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(34);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setForeground(TEXT);
        table.setBackground(CREAM);
        table.setGridColor(BORDER);
        table.setSelectionBackground(SAGE);
        table.setSelectionForeground(TEXT);
        table.setShowVerticalLines(false);
        table.setFillsViewportHeight(true);

        table.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );
        table.getTableHeader().setBackground(FOREST);
        table.getTableHeader().setForeground(CREAM);
        table.getTableHeader().setPreferredSize(new Dimension(0, 38));
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

    private JScrollPane createScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(BORDER));
        scroll.getViewport().setBackground(CREAM);
        return scroll;
    }

    private JButton createButton(String text, Color background) {

        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 16, 10, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JPanel createButtonBar(JButton... buttons) {

        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 12));
        bar.setOpaque(false);

        for (JButton button : buttons) {
            bar.add(button);
        }

        return bar;
    }
    
     
    private void showStudents() {

        setPage("Student Records",
                "Add, view, search, update and remove student information.");

        studentTable = createTable(
                new String[]{"Student ID", "Student Name", "Class"}
        );

        refreshStudentTable();

        JButton add = createButton("Add Student", FOREST);
        JButton update = createButton("Update Student", WALNUT);
        JButton remove = createButton("Remove Student", new Color(151, 83, 61));
        JButton search = createButton("Search Student", AMBER);

        add.addActionListener(e -> addStudent());
        update.addActionListener(e -> updateStudent());
        remove.addActionListener(e -> removeStudent());
        search.addActionListener(e -> searchStudent());

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.add(createScrollPane(studentTable), BorderLayout.CENTER);
        body.add(createButtonBar(add, update, remove, search), BorderLayout.SOUTH);

        contentPanel.add(body, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void refreshStudentTable() {

        if (studentTable == null) return;

        DefaultTableModel model =
                (DefaultTableModel) studentTable.getModel();

        model.setRowCount(0);

        for (Student student : studentManager.getAllStudents()) {
            model.addRow(new Object[]{
                    student.getstudentid(),
                    student.getstudentname(),
                    student.getstudentclass()
            });
        }
    }

    private void addStudent() {

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField classField = new JTextField();

        Object[] fields = {
                "Student ID:", idField,
                "Student Name:", nameField,
                "Class:", classField
        };

        int result = JOptionPane.showConfirmDialog(
                this, fields, "Add Student",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String studentClass = classField.getText().trim();

            if (id <= 0 || name.isEmpty() || studentClass.isEmpty()) {
                showError("Enter a valid ID, name and class.");
                return;
            }

            if (studentManager.searchStudent(id) != null) {
                showError("A student with this ID already exists.");
                return;
            }

            studentManager.addStudent(
                    new Student(name, studentClass, id)
            );

            refreshStudentTable();
            showDashboard();

        } catch (NumberFormatException ex) {
            showError("Student ID must be a number.");
        }
    }

    private void updateStudent() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Student ID to update:"
        );

        if (input == null) return;

        try {
            int id = Integer.parseInt(input.trim());
            Student student = studentManager.searchStudent(id);

            if (student == null) {
                showError("Student not found.");
                return;
            }

            JTextField nameField =
                    new JTextField(student.getstudentname());

            JTextField classField =
                    new JTextField(student.getstudentclass());

            Object[] fields = {
                    "New Name:", nameField,
                    "New Class:", classField
            };

            int result = JOptionPane.showConfirmDialog(
                    this, fields, "Update Student",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result != JOptionPane.OK_OPTION) return;

            String name = nameField.getText().trim();
            String studentClass = classField.getText().trim();

            if (name.isEmpty() || studentClass.isEmpty()) {
                showError("Name and class cannot be empty.");
                return;
            }

            studentManager.updateStudent(id, name, studentClass);
            refreshStudentTable();

        } catch (NumberFormatException ex) {
            showError("Enter a valid numeric student ID.");
        }
    }

    private void removeStudent() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Student ID to remove:"
        );

        if (input == null) return;

        try {
            int id = Integer.parseInt(input.trim());

            if (studentManager.searchStudent(id) == null) {
                showError("Student not found.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Remove this student and their attendance and marks records?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            studentManager.removeStudent(id);
            attendanceManager.removeStudentAttendance(id);
            performanceManager.removeMarks(id);

            refreshStudentTable();
            showDashboard();

        } catch (NumberFormatException ex) {
            showError("Enter a valid numeric student ID.");
        }
    }

    private void searchStudent() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Student ID to search:"
        );

        if (input == null) return;

        try {
            int id = Integer.parseInt(input.trim());
            Student student = studentManager.searchStudent(id);

            if (student == null) {
                showError("Student not found.");
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID: " + student.getstudentid()
                    + "\nName: " + student.getstudentname()
                    + "\nClass: " + student.getstudentclass(),
                    "Student Details",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {
            showError("Enter a valid numeric student ID.");
        }
    }
    
     
    private void showAttendance() {

        setPage("Attendance Management",
                "Record daily attendance and review student attendance history.");

        attendanceTable = createTable(
                new String[]{"Student ID", "Date", "Status"}
        );

        refreshAttendanceTable();

        JButton mark = createButton("Mark Attendance", FOREST);
        JButton summary = createButton("Student Summary", WALNUT);
        JButton refresh = createButton("Refresh", AMBER);

        mark.addActionListener(e -> markAttendance());
        summary.addActionListener(e -> attendanceSummary());
        refresh.addActionListener(e -> refreshAttendanceTable());

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.add(createScrollPane(attendanceTable), BorderLayout.CENTER);
        body.add(createButtonBar(mark, summary, refresh), BorderLayout.SOUTH);

        contentPanel.add(body, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void refreshAttendanceTable() {

        if (attendanceTable == null) return;

        DefaultTableModel model =
                (DefaultTableModel) attendanceTable.getModel();

        model.setRowCount(0);

        for (AttendanceRecord record : attendanceManager.getAllRecords()) {
            model.addRow(new Object[]{
                    record.getStudentId(),
                    record.getDate(),
                    record.getStatus()
            });
        }
    }

    private void markAttendance() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Student ID:"
        );

        if (input == null) return;

        try {
            int id = Integer.parseInt(input.trim());

            if (studentManager.searchStudent(id) == null) {
                showError("Student not found.");
                return;
            }

            Object[] choices = {"Present", "Absent"};

            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Select attendance status:",
                    "Mark Attendance",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,
                    choices[0]
            );

            if (choice == 0 || choice == 1) {
                boolean present = (choice == 0);

                boolean success = attendanceManager.markAttendance(
                        id, LocalDate.now(), present
                );

                if (success) {
                    refreshAttendanceTable();
                } else {
                    showError("Attendance could not be marked. "
                            + "It may already exist for today.");
                }
            }

        } catch (NumberFormatException ex) {
            showError("Enter a valid numeric student ID.");
        }
    }

    private void attendanceSummary() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Student ID:"
        );

        if (input == null) return;

        try {
            int id = Integer.parseInt(input.trim());

            if (studentManager.searchStudent(id) == null) {
                showError("Student not found.");
                return;
            }

            int present = attendanceManager.getPresentDays(id);
            int absent = attendanceManager.getAbsentDays(id);
            int total = attendanceManager.getTotalDays(id);
            double percentage =
                    attendanceManager.getAttendancePercentage(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID: " + id
                    + "\nPresent Days: " + present
                    + "\nAbsent Days: " + absent
                    + "\nTotal Days: " + total
                    + String.format("\nAttendance: %.2f%%", percentage),
                    "Attendance Summary",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {
            showError("Enter a valid numeric student ID.");
        }
    }
    
     
    private void showMarks() {

        setPage("Marks & Performance",
                "Enter subject marks and review academic performance.");

        marksTable = createTable(
                new String[]{
                        "Student ID", "Python", "Java",
                        "DAA", "ELX", "Percentage", "Grade"
                }
        );

        refreshMarksTable();

        JButton add = createButton("Add Marks", FOREST);
        JButton view = createButton("View Performance", WALNUT);
        JButton refresh = createButton("Refresh", AMBER);

        add.addActionListener(e -> addMarks());
        view.addActionListener(e -> viewPerformance());
        refresh.addActionListener(e -> refreshMarksTable());

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.add(createScrollPane(marksTable), BorderLayout.CENTER);
        body.add(createButtonBar(add, view, refresh), BorderLayout.SOUTH);

        contentPanel.add(body, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void refreshMarksTable() {

        if (marksTable == null) return;

        DefaultTableModel model =
                (DefaultTableModel) marksTable.getModel();

        model.setRowCount(0);

        for (SubjectMarks marks : performanceManager.getAllRecords()) {
            model.addRow(new Object[]{
                    marks.getStudentId(),
                    marks.getPython(),
                    marks.getJava(),
                    marks.getDaa(),
                    marks.getElx() == null ? "—" : marks.getElx(),
                    String.format("%.2f%%", marks.getPercentage()),
                    marks.getGrade()
            });
        }
    }

    private void addMarks() {

        JTextField idField = new JTextField();
        JTextField pythonField = new JTextField();
        JTextField javaField = new JTextField();
        JTextField daaField = new JTextField();
        JTextField elxField = new JTextField();

        Object[] fields = {
                "Student ID:", idField,
                "Python Marks:", pythonField,
                "Java Marks:", javaField,
                "DAA Marks:", daaField,
                "ELX Marks (optional):", elxField
        };

        int result = JOptionPane.showConfirmDialog(
                this, fields, "Add Academic Marks",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            int id = Integer.parseInt(idField.getText().trim());
            int python = Integer.parseInt(pythonField.getText().trim());
            int java = Integer.parseInt(javaField.getText().trim());
            int daa = Integer.parseInt(daaField.getText().trim());

            Integer elx = elxField.getText().trim().isEmpty()
                    ? null
                    : Integer.parseInt(elxField.getText().trim());

            if (studentManager.searchStudent(id) == null) {
                showError("Student not found. Add the student first.");
                return;
            }

            boolean success = performanceManager.addMarks(
                    id, python, java, daa, elx
            );

            if (success) {
                refreshMarksTable();
            } else {
                showError("Marks could not be added. Check the ID, "
                        + "marks range, and existing records.");
            }

        } catch (NumberFormatException ex) {
            showError("Enter valid numbers for the ID and marks.");
        }
    }

    private void viewPerformance() {

        String input = JOptionPane.showInputDialog(
                this, "Enter Student ID:"
        );

        if (input == null) return;

        try {
            int id = Integer.parseInt(input.trim());

            SubjectMarks marks = performanceManager.searchMarks(id);

            if (marks == null) {
                showError("No marks record found for this student.");
                return;
            }

            String elxText = marks.getElx() == null
                    ? "Not included"
                    : marks.getElx() + "/100";

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID: " + id
                    + "\nPython: " + marks.getPython() + "/100"
                    + "\nJava: " + marks.getJava() + "/100"
                    + "\nDAA: " + marks.getDaa() + "/100"
                    + "\nELX: " + elxText
                    + "\nTotal: " + marks.getTotal()
                    + "/" + marks.getMaximumMarks()
                    + String.format("\nPercentage: %.2f%%",
                            marks.getPercentage())
                    + "\nGrade: " + marks.getGrade(),
                    "Academic Performance",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {
            showError("Enter a valid numeric student ID.");
        }
    }

     
    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "StudentTrack",
                JOptionPane.WARNING_MESSAGE
        );
    }

     
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            StudentTrackGUI gui = new StudentTrackGUI();
            gui.setVisible(true);
        });
    }
}
