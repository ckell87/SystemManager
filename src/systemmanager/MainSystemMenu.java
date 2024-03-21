/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager;

import java.util.Scanner;
import systemmanager.User.ManageAdmin;
import systemmanager.User.UserAdmin;

/**Main menu created for each type of faculty.
 * username and password protected
 *each case will bring user to next menu once authenticated.
 * @author under
 */
public class MainSystemMenu {
    public static void displayMainMenu() {
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
                    String username = sc.nextLine();
                    System.out.print("Enter password ");
                    String password = sc.nextLine();
                    if (UserAdmin.authenticate(username, password)) {
                        System.out.println("Login successful. Welcome " + username);
                        ManageAdmin manageAdmin = new ManageAdmin();
                        manageAdmin.adminManager();
                    } else {
                        System.out.println("Login failed. Incorrect username or password.");
                    }
                    break;
                case 2:
                    //  Office user login
                    break;
                case 3:
                    //  Lecturer user login
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
