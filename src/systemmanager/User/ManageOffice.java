/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

import java.sql.SQLException;
import java.util.Scanner;
import systemmanager.DatabaseConnection;
import static systemmanager.Reports.CourseReport.generateCourseReport;
import static systemmanager.User.ManageAdmin.addNewUser;
import static systemmanager.User.ManageAdmin.deleteUser;
import static systemmanager.User.ManageAdmin.updateUser;

/**
 *
 * @author under
 */
public class ManageOffice {

    public void officeManager() throws SQLException {
        // main method for office user management
        Scanner sc = new Scanner(System.in);

        System.out.println("Office Manager Menu:");
        System.out.println("1. Change username");
        System.out.println("2. Change password");
        System.out.println("3. Generate Course report");
        System.out.println("4. Generate Student report");
        System.out.println("5. Generate Lecturer report");
        System.out.println("6. Back to main menu");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline character

        switch (choice) {
            case 1:
                changeUsername();
                break;
            case 2:
                changePassword();
                break;
            case 3:
                generateCourseReport();
                break;
            case 4:
                //generateStudentReport();
                break;
            case 5:
                //generateLecturerReport();
                break;
            case 6:
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void changeUsername() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter your password: ");
        String password = sc.nextLine().trim();

        if (DatabaseConnection.authenticateOfficeUser(username, password, "office")) { ////check user details before username change
            System.out.print("Enter your new username: ");
            String newUsername = sc.nextLine().trim();

            DatabaseConnection.updateUsername(username, newUsername);//// Update the username in the database
            System.out.println("Username updated successfully.");
        } else {
            System.out.println("update failed. Incorrect username, password, or user role.");
        }
    }

    public void changePassword() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter your password: ");
        String password = sc.nextLine().trim();
        if (DatabaseConnection.authenticateOfficeUser(username, password, "office")) { //check user details before username change
            System.out.print("Enter your new password: ");
            String newPassword = sc.nextLine().trim();

            DatabaseConnection.updatePassword(username, newPassword); // Update the password in the database
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Authentication failed. Incorrect username, password, or user role.");
        }
    }
}
