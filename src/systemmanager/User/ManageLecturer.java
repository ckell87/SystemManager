/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

import java.sql.SQLException;
import java.util.Scanner;
import systemmanager.DatabaseConnection;

/**
 *
 * @author under
 */
public class ManageLecturer {

    private final ManageOffice officeManager;

    public ManageLecturer() {
        this.officeManager = new ManageOffice();
    }

    public void lecturerManager() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Lecturer Manager Menu:");
        System.out.println("1. Change username");
        System.out.println("2. Change password");
        System.out.println("3. Generate Lecturer report");
        System.out.println("4. Back to main menu");
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
                generateLecturerReport();
                break;
            case 4:
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void changeUsername() throws SQLException {
        officeManager.changeUsername();
    }

    public void changePassword() throws SQLException {
        officeManager.changePassword();
    }

    public void generateLecturerReport() {
        
    }
}