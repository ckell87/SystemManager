/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

import java.sql.SQLException;
import java.util.Scanner;
import systemmanager.DatabaseConnection;
import systemmanager.Reports.LecturerReport;

/**
 *
 * @author under
 */
public class ManageLecturer {

    private final ManageOffice officeManager;

    public ManageLecturer() {
        this.officeManager = new ManageOffice();// new manager object created to utilise change username/pssword methods

    }

    public void lecturerManager() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Lecturer Manager Menu:"); //lectuer sub menu for access to lectuer only options
            System.out.println("1. Change username");
            System.out.println("2. Change password");
            System.out.println("3. Generate Lecturer report");
            System.out.println("4. Back to main menu");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    changeUsername(); // call method to change username
                    break;
                case 2:
                    changePassword();// call method to change password
                    break;
                case 3:
                    LecturerReport.generateLecturerReport();// call method to generate report
                    break;
                case 4:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4); // loop back to sub menu until return to main menu chosen

    }

    public void changeUsername() throws SQLException {
        officeManager.changeUsername(); // utalise method in officemanager to change username
    }

    public void changePassword() throws SQLException { //// utilise method in officemanager to change username
        officeManager.changePassword();
    }

}
