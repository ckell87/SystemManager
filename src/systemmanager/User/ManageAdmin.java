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

        System.out.println("Admin Manager Menu:");
        System.out.println("1. Add new user");
        System.out.println("2. Delete user");
        System.out.println("3. Alter User details");
        System.out.println("4. Back to main menu");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                addNewUser();
                break;
            case 2:
                deleteUser();
                break;
            case 3:
                updateUser();
            case 4:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void addNewUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name ");
        String first_name = sc.nextLine().toLowerCase();
        System.out.println("Enter last name ");
        String last_name = sc.nextLine().toLowerCase();
        System.out.println("Enter username ");
        String username = sc.nextLine();
        System.out.println("Enter password ");
        String password = sc.nextLine();
        System.out.println("Enter role  ");
        String role = sc.nextLine().toLowerCase();

                        // Insert new user into database
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
                        // Select the CA2 database
            stmt.execute("USE CA2;");

                         // Insert new user into newUsers table
            String query = "INSERT INTO newUsers (first_name, last_name, username, password, role) VALUES ('" + first_name + "', '" + last_name + "', '" + username + "', '" + password + "', '" + role + "')";
            stmt.executeUpdate(query);

            System.out.println("New user added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding new user " + e.getMessage());
        }
    }

    public static void deleteUser() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter last name of user for deletion ");
        String last_name = sc.nextLine().toLowerCase();
        System.out.println("Enter username of user for deletion ");
        String username = sc.nextLine().toLowerCase();
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");
            String deleteUser = "DELETE FROM newUsers WHERE last_name = ? AND username = ?"; //placeholders used for security
            try ( PreparedStatement pstmt = conn.prepareStatement(deleteUser)) {
                pstmt.setString(1, last_name);
                pstmt.setString(2, username);

                int rows = pstmt.executeUpdate(); // checks for row modification  to establish sucessful excecution
                if (rows > 0) {
                    System.out.println("Username: " + username + " deleted ");
                } else {
                    System.out.println("User last name " + last_name + " and username: " + username + "not found.");
                }
            }
        }

    }

    public static void updateUser() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter last name of user ");
        String last_name = sc.nextLine().toLowerCase();
        System.out.println("Enter username of user  ");
        String username = sc.nextLine().toLowerCase();
        System.out.println("Enter new role for user  ");
        String new_role = sc.nextLine().toLowerCase();

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");
            String updateUser = "UPDATE newUsers SET role = ? WHERE last_name = ? AND username = ?";
            try ( PreparedStatement pstmt = conn.prepareStatement(updateUser)) {
                pstmt.setString(1, new_role);
                pstmt.setString(2, last_name);
                pstmt.setString(3, username);
                int rows = pstmt.executeUpdate();// checks for row modification  to establish sucessful excecution
                if (rows > 0) {
                    System.out.println("Username: " + username + " updated to " + new_role + " role ");
                } else {
                    System.out.println("User " + last_name + " and username " + username + " not found ");
                }
            }
        }
    }

}
