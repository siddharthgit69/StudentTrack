# StudentTrack – Team Work Division

## Project Overview
**Project Title:** Student Attendance and Academic Performance Management System  
**Project Name:** StudentTrack  
**Language:** Java  
**Interface:** Java Swing (Desktop Application)  
**Storage:** Local File Handling  

The application will manage student records, attendance, marks and academic performance through a single desktop application.

---

## PART 1 – Member 1: Student Management (Siddharth)

**Responsibility:** Manage student information and records.

**Tasks:**
1. Create and maintain the `Student` class.
2. Add students using `ArrayList`.
3. Search students using their ID.
4. Display all student records.
5. Update student details.
6. Delete student records.
7. Prevent duplicate student IDs.
8. Validate user input.

**Suggested Files:**
- `Student.java`
- `StudentManager.java`

**Expected Result:** A functional student management module supporting Add, Search, Display, Update and Delete operations.

---

## PART 2 – Member 2: Attendance Management (Jashveer)

**Responsibility:** Develop the student attendance module.

**Tasks:**
1. Record attendance using student ID.
2. Mark students as Present or Absent.
3. Store attendance dates.
4. Display individual attendance records.
5. Calculate total present and absent days.
6. Calculate attendance percentage.
7. Prevent duplicate attendance entries for the same student on the same date.

**Suggested Files:**
- `AttendanceRecord.java`
- `AttendanceManager.java`

**Expected Result:** A functional attendance module that records and calculates student attendance.

---

## PART 3 – Member 3: Marks and Academic Performance(Jivitesh)

**Responsibility:** Develop the marks and grading module.

**Tasks:**
1. Enter marks for different subjects.
2. Associate marks with student IDs.
3. Display subject-wise marks.
4. Calculate total marks and average percentage.
5. Assign grades based on an agreed grading system.
6. Validate marks to prevent invalid entries.
7. Display individual student performance.

**Suggested Files:**
- `SubjectMarks.java`
- `PerformanceManager.java`

**Expected Result:** A functional academic performance module that calculates marks, percentages and grades.

---

## PART 4 – Member 4: GUI, File Handling and Integration(BODA)

**Responsibility:** Develop the desktop interface and integrate all modules.

**Tasks:**
1. Create the desktop GUI using Java Swing.
2. Design the main application window.
3. Add navigation for Student Management, Attendance and Marks.
4. Connect the GUI to the other members' modules.
5. Implement file handling to save student, attendance and marks records.
6. Load previously saved records when the application starts.
7. Test the complete application and fix integration issues.

**Suggested Files:**
- `MainFrame.java`
- `FileManager.java`
- `Main.java` (final application entry point)

**Expected Result:** A working desktop application with an integrated GUI and persistent data storage.

---

## Team Coordination and GitHub Rules

1. Each member is responsible for developing and testing their assigned module.
2. Commit and push changes regularly.
3. Avoid editing another member's files without coordination.
4. Inform the team before changing shared class names, fields or method signatures.
5. All modules must be integrated and tested before the final demonstration.
6. Every member must understand their contribution and be able to explain it during evaluation.

## Development Order

1. Complete individual Java modules.
2. Integrate student, attendance and performance modules.
3. Implement file storage.
4. Connect all modules to the Swing GUI.
5. Perform testing and debugging.
6. Complete documentation and prepare the final demonstration.

**Note:** All members are expected to contribute working Java code, test their modules and understand the overall project.