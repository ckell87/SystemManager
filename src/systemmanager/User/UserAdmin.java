/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager.User;

/**
 *
 * @author under
 */
public class UserAdmin {
    private String username;
    private String password;

    public static boolean authenticateFirstUser(String username, String password, String role) {
        return username.equals("admin") && password.equals("java") && role.equals("admin");
}
}