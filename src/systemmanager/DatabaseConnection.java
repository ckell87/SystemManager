/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author under
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";
    
   /* public void newUserTable(String newUsers) throws SQLException{
       try {
           Connection conn = DriverManager.getConnection( DB_URL, USER, PASSWORD);
          Statement stmt = conn.createStatement();
          stmt.execute("USE CA2;");
          stmt.execute("CREATE TABLE " + newUsers + " (first_name VARCHAR(25), last_name VARCHAR(25), username VARCHAR(25), password VARCHAR(25), role VARCHAR(25)); " );
           System.out.println("table sucessfully creted ");
           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

    }
    */
    public static void addNewUser() {
    Scanner sc = new Scanner(System.in);
     System.out.println("Enter first name ");
    String first_name = sc.nextLine();
    System.out.println("Enter last name ");
    String last_name = sc.nextLine();
    System.out.println("Enter username: ");
    String username = sc.nextLine();
    System.out.println("Enter password: ");
    String password = sc.nextLine();
    System.out.println("Enter role: ");
    String role = sc.nextLine();
    
    // Insert new user into database
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {
            // Select the CA2 database
            stmt.execute("USE CA2;");

            // Insert new user into newUsers table
            String query = "INSERT INTO newUsers (first_name, last_name, username, password, role) VALUES ('" + first_name + "', '" + last_name + "', '" + username + "', '" + password + "', '" + role + "')";
            stmt.executeUpdate(query);

            System.out.println("New user added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding new user: " + e.getMessage());
    }
}

    
}
