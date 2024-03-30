package systemmanager.Reports;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LecturerReport {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/CA2";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";

    public static void generateLecturerReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter either txt or csv for file output "); // ask user for file output preference
        String fileFormat = scanner.nextLine().trim().toLowerCase();

        if (!fileFormat.equals("txt") && !fileFormat.equals("csv")) {
            System.out.println("Invalid, Please enter txt or csv");
            return;
        }

        String fileName = "lecturer_Report." + fileFormat;//output file name

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement();  FileWriter writer = new FileWriter(fileName)) {

            if (fileFormat.equals("csv")) { //file headers
                writer.append("Lecturer name, Title, Module1, Module1 size, Module2, Module2 size, Module3, Module3 size, Module4, Module4 size, Class type\n");
            }
            //SQL quearies to extract data from tables and columns
            ResultSet staff = stmt.executeQuery("SELECT s.fname, s.lname, s.title, "
                    + "s.module_1, s.module_1 AS module1_size, "
                    + "s.module_2, s.module_2 AS module2_size, "
                    + "s.module_3, s.module_3 AS module3_size, "
                    + "s.module_4, s.module_4 AS module4_size, "
                    + "s.class_type "
                    + "FROM staff s WHERE s.role = 'lecturer'");

            while (staff.next()) {
                String lecturerName = staff.getString("fname") + " " + staff.getString("lname");
                String title = staff.getString("title");
                String module1 = staff.getString("module_1");
                int module1Size = getClassSize(staff.getString("module1_size")); // calls method to get amount of students per class
                String module2 = staff.getString("module_2");
                int module2Size = getClassSize(staff.getString("module2_size"));
                String module3 = staff.getString("module_3");
                int module3Size = getClassSize(staff.getString("module3_size"));
                String module4 = staff.getString("module_4");
                int module4Size = getClassSize(staff.getString("module4_size"));
                String classType = staff.getString("class_type");
                String mod1SizeStr = String.valueOf(module1Size);
                String mod2SizeStr = String.valueOf(module2Size);
                String mod3SizeStr = String.valueOf(module3Size);
                String mod4SizeStr = String.valueOf(module4Size);

                if (fileFormat.equals("csv")) { //write out to csv file
                    writer.append(String.join(",", lecturerName, title, module1, String.valueOf(module1Size), module2, String.valueOf(module2Size), module3, String.valueOf(module3Size), module4, String.valueOf(module4Size), classType));
                    writer.append("\n");
                } else if (fileFormat.equals("txt")) { // append each String to the file then move to next line
                    writer.append("Lecturer Name: ").append(lecturerName).append("\n");
                    writer.append("Title: ").append(title).append("\n");
                    writer.append("Module1: ").append(module1).append(", Size: ").append(mod1SizeStr).append("\n");
                    writer.append("Module2: ").append(module2).append(", Size: ").append(mod2SizeStr).append("\n");
                    writer.append("Module3: ").append(module3).append(", Size: ").append(mod3SizeStr).append("\n");
                    writer.append("Module4: ").append(module4).append(", Size: ").append(mod4SizeStr).append("\n");
                    writer.append("Class Type: ").append(classType).append("\n");
                }
            }

            System.out.println("Lecturer report generated. File: " + fileName);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to get class size. iterate over the commas and return each number
     * if not empty
     *
     */
    private static int getClassSize(String sizeString) {
        if (sizeString == null || sizeString.isEmpty()) {
            return 0;
        }

        int commaAmnt = 0;
        for (int i = 0; i < sizeString.length(); i++) {
            if (sizeString.charAt(i) == ',') {
                commaAmnt++;
            }
        }

        return commaAmnt + 1;
    }

}
