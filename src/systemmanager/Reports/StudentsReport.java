package systemmanager.Reports;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentsReport {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/CA2";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";

    public static void generateStudentsReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter either txt or csv for file output "); // ask user for file output preference
        String fileFormat = scanner.nextLine().trim().toLowerCase();

        if (!fileFormat.equals("txt") && !fileFormat.equals("csv")) {
            System.out.println("Invalid, Please enter txt or csv");
            return;
        }
        // File name 
        String fileName = "Students_Report" + fileFormat; //output file name

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement();  FileWriter writer = new FileWriter(fileName)) {
            // validate fille type from user input
            if (fileFormat.equals("csv")) { // file headers
                writer.append("Student Name, Programme, Module1, Module1 Grade, Module2, Module2 Grade, Module3, Module3 Grade, Module4, Module4 Grade\n");
            }
            // SQL queary to retrieve data for  student report
            ResultSet studentInfo = stmt.executeQuery("SELECT s.student_fname, s.student_lname, c.course_name, "
                    + "s.module_1, g.module_1 AS grade_1, "
                    + "s.module_2, g.module_2 AS grade_2, "
                    + "s.module_3, g.module_3 AS grade_3, "
                    + "s.module_4, g.module_4 AS grade_4 "
                    + "FROM students s "
                    + "JOIN courses c ON s.course_id = c.course_id "
                    + "LEFT JOIN grades g ON s.student_id = g.student_id");

            while (studentInfo.next()) { //iterate over resultset coverts them to strings
                String studentName = studentInfo.getString("student_fname") + " " + studentInfo.getString("student_lname");
                String programme = studentInfo.getString("course_name");
                String module1 = studentInfo.getString("module_1");
                String grade1 = getGradeString(studentInfo.getString("grade_1"));// calls method to get class size for module_1
                String module2 = studentInfo.getString("module_2");
                String grade2 = getGradeString(studentInfo.getString("grade_2"));
                String module3 = studentInfo.getString("module_3");
                String grade3 = getGradeString(studentInfo.getString("grade_3"));
                String module4 = studentInfo.getString("module_4");
                String grade4 = getGradeString(studentInfo.getString("grade_4")); 

                if (fileFormat.equals("csv")) { // write data to file in csv format
                    writer.append(String.join(",", studentName, programme, module1, grade1, module2, grade2, module3, grade3, module4, grade4));
                    writer.append("\n");
                } else if (fileFormat.equals("txt")) { // write data to file if txt format
 
                    // append each String to the file, insert comma, then move to next line
                    writer.append("Student Name: ").append(studentName).append("\n");
                    writer.append("Programme: ").append(programme).append("\n");
                    writer.append("Module1: ").append(module1).append(", Grade: ").append(grade1).append("\n");
                    writer.append("Module2: ").append(module2).append(", Grade: ").append(grade2).append("\n");
                    writer.append("Module3: ").append(module3).append(", Grade: ").append(grade3).append("\n");
                    writer.append("Module4: ").append(module4).append(", Grade: ").append(grade4).append("\n");
                }
            }

            System.out.println("students report generated. File: " + fileName);

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // print where error occured
        }
    }
/**
*method to retrive grades which are less than 40 and return that they need "to be repeated".
* any null values indicated that module is "to be completed"
 */
    private static String getGradeString(String grade) {
        if (grade == null || grade.isEmpty()) {
            return "to be completed";
        } else {
            try {
                double gradeAmount = Double.parseDouble(grade);
                if (gradeAmount < 40.0) {
                    return "to be repeated";
                } else {
                    return grade;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade format: " + grade);
                return "to be completed";
            }
        }
    }
}
