/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author under
 */
public class DatabaseConnection { //hard coded details to connect to local database

    private static final String DB_URL = "jdbc:mysql://localhost";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";

    public void alterTable() throws SQLException { // username, password and rolle columns added to staff table
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement(); // allow for MySql queries to be sent to databse
            stmt.execute("USE CA2;"); // execute use databse;
            stmt.execute(" ALTER TABLE staff " + " ADD COLUMN username VARCHAR(25) AFTER lname, "
                    + " ADD COLUMN password VARCHAR(25) AFTER username, "
                    + " ADD COLUMN role VARCHAR(25) AFTER password; ");
            System.out.println("column sucessfully created");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void renameTable() { // rename mysql table

        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");
            stmt.execute("RENAME TABLE  lecturer  TO  staff;");
            System.out.println("Table renamed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * Authenticates  Admin users against details stored in the database.
 *  Their username, password and role are provided by the user.
 *  If they match, user authentication is successful, Admin sub console menu is opened
 * method is used to authorise  username and password change in ManageAdmin class also.
 *  SQLException If an SQL exception occurs during database operations.
 */
    public static boolean authenticateAdminUser(String username, String password, String role) throws SQLException { // Check if username, password and role match the input
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            try ( Statement stmt = conn.createStatement()) {
                stmt.execute("USE CA2;");
                String findDetails = "SELECT username, password, role FROM staff WHERE username = ? AND password = ?"; //username and password hidden using placeholders
                try ( PreparedStatement pstmt = conn.prepareStatement(findDetails)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    try ( ResultSet rs = pstmt.executeQuery()) { ////details crossed checked by database
                        if (rs.next()) {
                            String findUsername = rs.getString("username");
                            String findPassword = rs.getString("password");
                            String findRole = rs.getString("role");

                            if (username.equals(findUsername) && password.equals(findPassword) && role.equalsIgnoreCase("admin")) {
                                return true; // login details match
                            } else {
                                return false; // login failed, return to main menu
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                return false;
            }
        }
    }
 /**
 * Authenticates an Office user against details stored in the database.
 *  Their username, password and role are provided by the user.
 *  If they match, user authentication is successful, Office sub console menu is opened
 * method is used to authorise only their own username and password change in ManageOffice class also.
 *  SQLException If an SQL exception occurs during database operations.
 */
    public static boolean authenticateOfficeUser(String username, String password, String role) throws SQLException { // Check if username, password and role match the input
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            try ( Statement stmt = conn.createStatement()) {
                stmt.execute("USE CA2;");
                String findDetails = "SELECT username, password, role FROM staff WHERE username = ? AND password = ?"; //username and password hidden using placeholders
                try ( PreparedStatement pstmt = conn.prepareStatement(findDetails)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    try ( ResultSet rs = pstmt.executeQuery()) { //details crossed checked by database
                        if (rs.next()) {
                            String findUsername = rs.getString("username");
                            String findPassword = rs.getString("password");
                            String findRole = rs.getString("role");

                            if (username.equals(findUsername) && password.equals(findPassword) && role.equalsIgnoreCase("office")) { 
                                return true; // login details match
                            } else {
                                return false; // login failed, return to main menu
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                return false;
            }
        }
    }
    /**
 * Authenticates a lecturer user against details stored in the database.
 *  The username, password and role are provided by the user.
 *  If they match, user authentication is successful, Lecturer sub console menu is opened
 * method is used to authorise only their own username and password change located in the ManageLecturer class also.
 *  SQLException If an SQL exception occurs during database operations.
 */
public static boolean authenticateLecturer(String username, String password, String role) throws SQLException { // Check if username, password and role match the input
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            try ( Statement stmt = conn.createStatement()) {
                stmt.execute("USE CA2;");
                String findDetails = "SELECT username, password, role FROM staff WHERE username = ? AND password = ?"; //username and password hidden using placeholders
                try ( PreparedStatement pstmt = conn.prepareStatement(findDetails)) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    try ( ResultSet rs = pstmt.executeQuery()) { // details crossed checked by database
                        if (rs.next()) {                           
                            String findUsername = rs.getString("username");
                            String findPassword = rs.getString("password");
                            String findRole = rs.getString("role");

                            if (username.equals(findUsername) && password.equals(findPassword) && role.equalsIgnoreCase("lecturer")) {
                                return true; 
                            } else {
                                return false; // login failed, return to main menu
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                return false;
            }
        }
    }

/**
 * method used by all three users
 *  takes verified information from user management class and inserts new usnername in correct database column.
*/
    public static void updateUsername(String username, String newUsername) throws SQLException {
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");
            String updatePassword = "UPDATE staff SET username = ? WHERE username = ?"; //table and column name for updated username. placeholders used for sensitive data
            try ( PreparedStatement pstmt = conn.prepareStatement(updatePassword)) {
                pstmt.setString(1, newUsername);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            }
        }
    }

    /**
 * method used by all three users
 *  takes verified information from user management class and inserts new password in correct database column.
*/
    
    public static void updatePassword(String username, String newPassword) throws SQLException {
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);  Statement stmt = conn.createStatement()) {
            stmt.execute("USE CA2;");
            String updatePassword = "UPDATE staff SET password = ? WHERE username = ?"; ////table and column name for updated username. placeholders used for sensitive data
            try ( PreparedStatement pstmt = conn.prepareStatement(updatePassword)) {
                pstmt.setString(1, newPassword);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            }
        }
    }
    
   
}
