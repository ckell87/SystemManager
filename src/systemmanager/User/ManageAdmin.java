/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import systemmanager.DatabaseConnection;

/**
 *
 * @author under
 */
public class ManageAdmin {

    private static final String DB_URL = "jdbc:mysql://localhost";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";

    public void adminManager() throws SQLException {
        // main method for admin user management
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Admin Manager Menu:");
            //admin sub menu
            System.out.println("1. Add new user");
            System.out.println("2. Delete user");
            System.out.println("3. Alter User details");
            System.out.println("4. Back to main menu");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addNewUser();// call method to add new user
                    break;
                case 2:
                    deleteUser(); // call method to delete users
                    break;
                case 3:
                    updateUser();// call method to update user details
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4); // loop back to sub menu
    }

    public static void addNewUser() { //method to distingush which staff member to add. will direct to appropriate specific add user method.
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter role of new user (must be admin, office, or lecturer)");
        String role = sc.nextLine().toLowerCase();

        switch (role) {
            case "admin":
                addAdminUser();
                break;
            case "office":
                addOfficeUser();
                break;
            case "lecturer":
                addLecturer();
                break;
            default:
                System.out.println("Invalid role entered");
                break;

        }
    }

    public static void addAdminUser() { // Add new office user details to database. first name, last name, username, password, (role automatically added as admin)
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name ");
        String fname = sc.nextLine().toLowerCase();
        System.out.println("Enter last name ");
        String lname = sc.nextLine().toLowerCase();
        System.out.println("Enter username ");
        String username = sc.nextLine();
        System.out.println("Enter password ");
        String password = sc.nextLine();

        // Insert new user into database
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            // Select the CA2 database
            stmt.execute("USE CA2;");
            String addAdminUser = "INSERT INTO staff (fname, lname, username, password, role) VALUES ('" + fname + "', '" + lname + "', '" + username + "', '" + password + "', ' admin ')";
            stmt.executeUpdate(addAdminUser); // Insert new user into staff table

            System.out.println(fname + lname + " added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding" + fname + lname + e.getMessage());// method for exception message. Find source of error
        }
    }

    public static void addOfficeUser() { // Add new office user details to database. first name, last name, username, password, (role automatically added as office)
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name ");
        String fname = sc.nextLine().toLowerCase();
        System.out.println("Enter last name ");
        String lname = sc.nextLine().toLowerCase();
        System.out.println("Enter username ");
        String username = sc.nextLine();
        System.out.println("Enter password ");
        String password = sc.nextLine();

        // Insert new user into database
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            // Select the CA2 database
            stmt.execute("USE CA2;");
            String addOfficeUser = "INSERT INTO staff (fname, lname, username, password, role) VALUES ('" + fname + "', '" + lname + "', '" + username + "', '" + password + "',  ' office ')";
            stmt.executeUpdate(addOfficeUser); // Insert new user into staff table

            System.out.println(fname + lname + " added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding" + fname + lname + e.getMessage());
        }
    }

    public static void addLecturer() { // method to add all lecturers details from user (role automatically added as lecturer)
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name ");
        String fname = sc.nextLine().toLowerCase();
        System.out.println("Enter last name ");
        String lname = sc.nextLine().toLowerCase();
        System.out.println("Enter username ");
        String username = sc.nextLine();
        System.out.println("Enter password ");
        String password = sc.nextLine();
        System.out.println("Enter title ");
        String title = sc.nextLine().toLowerCase();
        System.out.println("Enter module1 ");
        String module1 = sc.nextLine().toLowerCase();
        System.out.println("Enter module2 ");
        String module2 = sc.nextLine();
        System.out.println("module3 ");
        String module3 = sc.nextLine();
        System.out.println("module4 ");
        String module4 = sc.nextLine().toLowerCase();
        System.out.println("class type ");
        String classType = sc.nextLine();
        System.out.println("course id ");
        int course_id = sc.nextInt();

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            // Select the CA2 database
            stmt.execute("USE CA2;");// SQL query to add new lecturer details to database
            String addLecturer = "INSERT INTO staff (fname, lname, username, password, role, title, module_1, module_2, module_3, module_4, class_Type, course_id) "
                    + "VALUES ('" + fname + "', '" + lname + "', '" + username + "', '" + password + "', ' lecturer ', '"
                    + title + "', '" + module1 + "', '" + module2 + "', '" + module3 + "', '" + module4
                    + "', '" + classType + "', " + course_id + ")";
            stmt.executeUpdate(addLecturer); // Insert new user into staff table

            System.out.println(fname + lname + " added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding" + fname + lname + e.getMessage()); // method for exception message. Find source of error
        }
    }

    public static void deleteUser() throws SQLException { //method to delete user once last name and username match
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter last name of user for deletion ");
        String lname = sc.nextLine().toLowerCase();
        System.out.println("Enter username of user for deletion ");
        String username = sc.nextLine().toLowerCase();
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");// SQL query to delete details to database
            String deleteUser = "DELETE FROM staff WHERE lname = ? AND username = ?"; //placeholders used for security
            try ( PreparedStatement pstmt = conn.prepareStatement(deleteUser)) {
                pstmt.setString(1, lname);
                pstmt.setString(2, username);

                int rows = pstmt.executeUpdate(); // checks for row modification  to establish sucessful excecution
                if (rows > 0) {
                    System.out.println("Username: " + username + " deleted "); //deletion confirmation
                } else {
                    System.out.println("User last name " + lname + " and username: " + username + "not found.");
                }
            }
        }

    }

    public static void updateUser() throws SQLException {  //method to update user role once last name and username match
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter last name of user ");
        String lname = sc.nextLine().toLowerCase();
        System.out.println("Enter username of user  ");
        String username = sc.nextLine().toLowerCase();
        System.out.println("Enter new role for user  ");
        String new_role = sc.nextLine().toLowerCase();

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");// SQL query to update details to database
            String updateUser = "UPDATE staff SET role = ? WHERE lname = ? AND username = ?";
            try ( PreparedStatement pstmt = conn.prepareStatement(updateUser)) {
                pstmt.setString(1, new_role);
                pstmt.setString(2, lname);
                pstmt.setString(3, username);
                int rows = pstmt.executeUpdate();// checks for row modification  to establish sucessful excecution
                if (rows > 0) {
                    System.out.println("Username: " + username + " updated to " + new_role + " role ");
                } else {
                    System.out.println("User " + lname + " and username " + username + " not found ");
                }
            }
        }
    }

}
