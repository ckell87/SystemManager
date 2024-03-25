/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.Reports;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CourseReport {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/CA2";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";

    public static void generateCourseReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the desired file format (txt/csv): ");
        String fileFormat = scanner.nextLine().trim().toLowerCase();

        // Validate user input for file format
        if (!fileFormat.equals("txt") && !fileFormat.equals("csv")) {
            System.out.println("Invalid file format. Please enter 'txt' or 'csv'.");
            return;
        }

        String fileName = "Course_Report." + fileFormat;

        // SQL query to retrieve data from different tables
        String query = "SELECT "
                + "s.module AS module, "
                + "c.course_name AS course_name, "
                + "COUNT(DISTINCT s.student_id) AS num_students_enrolled, "
                + "CONCAT(staff.fname, ' ', staff.lname) AS lecturer_name, "
                + "c.room_number AS room_assigned "
                + "FROM "
                + "(SELECT course_id, module_1 AS module, student_id FROM students "
                + "UNION ALL "
                + "SELECT course_id, module_2, student_id FROM students "
                + "UNION ALL "
                + "SELECT course_id, module_3, student_id FROM students "
                + "UNION ALL "
                + "SELECT course_id, module_4, student_id FROM students) AS s "
                + "JOIN courses c ON s.course_id = c.course_id "
                + "LEFT JOIN staff ON s.module IN (staff.module_1, staff.module_2, staff.module_3, staff.module_4) "
                + "GROUP BY s.module, c.course_name, staff.fname, staff.lname, c.room_number "
                + "ORDER BY s.module";

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(query)) {

            // Create a FileWriter to write to the specified file format
            FileWriter writer = new FileWriter(fileName);

            // Write headers to the file
            if (fileFormat.equals("csv")) {
                writer.append("Module, Course Name, Number of Students Enrolled, Lecturer Name, Room Assigned\n");
            }

            // Process each row and write to the file
            while (rs.next()) {
                String module = rs.getString("module");
                String courseName = rs.getString("course_name");
                int numStudentsEnrolled = rs.getInt("number_of_students_enrolled");
                String lecturerName = rs.getString("lecturer_name");
                String roomAssigned = rs.getString("room_assigned");

                // Write data to the file
                if (fileFormat.equals("csv")) {
                    writer.append(String.join(",", module, courseName, String.valueOf(numStudentsEnrolled), lecturerName, roomAssigned));
                    writer.append("\n");
                } else if (fileFormat.equals("txt")) {
                    writer.append(module + ", " + courseName + ", " + numStudentsEnrolled + ", " + lecturerName + ", " + roomAssigned);
                    writer.append("\n");
                }
            }

            // Close FileWriter
            writer.close();

            System.out.println("Course report generated successfully. File: " + fileName);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
