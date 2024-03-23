/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

import java.sql.SQLException;
import java.util.Scanner;
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
        System.out.println("1. Change username ");
        System.out.println("2. Change password");
        System.out.println("3. Generate Course report");
        System.out.println("4. Generate Student report");
        System.out.println("3. Generate Lecturer report");
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
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
