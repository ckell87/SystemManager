/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager;

import java.sql.SQLException;
import java.util.Scanner;
import systemmanager.User.ManageAdmin;
import systemmanager.DatabaseConnection;
import systemmanager.User.ManageLecturer;
import systemmanager.User.ManageOffice;

/**
 * Main menu created for each type of faculty. username and password protected
 * each case will bring user to next menu once authenticated.
 *
 * @author under
 */
public class MainSystemMenu {

    public static void displayMainMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose a number from the menu below");
            System.out.println("1. Admin user");
            System.out.println("2. Office user");
            System.out.println("3. Lecturer user");
            System.out.println("4. Exit menu");

            int choice = sc.nextInt();
            sc.nextLine(); // newline character before next user input

            switch (choice) {
                case 1:
                    System.out.print("Enter username ");
                    String adminUsername = sc.nextLine().toLowerCase().trim();// remove white space and change to all lower caps
                    System.out.print("Enter password ");
                    String adminPassword = sc.nextLine().toLowerCase().trim();
                    System.out.print("Enter role (either admin, office or lecturer) ");
                    String adminRole = sc.nextLine().toLowerCase().trim();
                    if (DatabaseConnection.authenticateAdminUser(adminUsername, adminPassword, adminRole)) { // role used to check against database to authorise correct sub menu i.e admin role
                        System.out.println("Login successful. Welcome " + adminUsername);
                        ManageAdmin manageAdmin = new ManageAdmin();
                        manageAdmin.adminManager(); //call up admin console menu
                    } else {
                        System.out.println("Login failed. Incorrect username, password or user "); // will go back to main menu again
                    }
                    break;
                case 2:
                    System.out.print("Enter username ");
                    String officeUsername = sc.nextLine().toLowerCase().trim();
                    System.out.print("Enter password ");
                    String officePassword = sc.nextLine().toLowerCase().trim();
                    System.out.print("Enter role (either admin, office or lecturer) ");
                    String officeRole = sc.nextLine().toLowerCase().trim();
                    if (DatabaseConnection.authenticateOfficeUser(officeUsername, officePassword, officeRole)) { // role used to check against database to authorise correct sub menu ie. office role
                        System.out.println("Login successful. Welcome " + officeUsername);
                        ManageOffice manageOffice = new ManageOffice();
                        manageOffice.officeManager(); // call up office console menu
                    } else {
                        System.out.println("Login failed. Incorrect username, password or user ");
                    }
                    break;
                case 3:
                    System.out.print("Enter username ");
                    String lecturerUsername = sc.nextLine().toLowerCase().trim();
                    System.out.print("Enter password ");
                    String lecturerPassword = sc.nextLine().toLowerCase().trim();
                    System.out.print("Enter role (either admin, office or lecturer) ");
                    String lecturerRole = sc.nextLine().toLowerCase().trim();
                    if (DatabaseConnection.authenticateLecturer(lecturerUsername, lecturerPassword, lecturerRole)) { //// role used to check against database to authorise correct sub menu ie. lecturer role
                        System.out.println("Login successful. Welcome " + lecturerUsername);
                        ManageLecturer manageLecturer = new ManageLecturer();
                        manageLecturer.lecturerManager(); // call up lecturer console menu
                    }
                    break;
                case 4:
                    System.out.println("Exiting menu");
                    System.exit(0);
                default:
                    System.out.println("Unknown user. Please try again.");
            }
        }
    }
}
