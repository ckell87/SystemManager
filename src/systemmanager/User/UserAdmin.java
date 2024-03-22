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

   /* public static boolean authenticateFirstUser(String username, String password, String role) {
      try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
        String query = "SELECT username, password, role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String retrievedUsername = rs.getString("username");
                    String retrievedPassword = rs.getString("password");
                    String role = rs.getString("role");
                    // Check if retrieved username and password match the input, and role is "admin"
                    if (username.equals(retrievedUsername) && password.equals(retrievedPassword) && role.equals("admin")) {
                        return true; // Authentication successful
                    }
                }*/
}
