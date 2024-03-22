/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

import java.util.Scanner;
import systemmanager.DatabaseConnection;

/**
 *
 * @author under
 */
public class ManageAdmin {

    public void adminManager() {
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
                DatabaseConnection.addNewUser();
                break;
            case 2:
                //  delete user
                break;
            case 3:
            // alter user details
            case 4:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

}
