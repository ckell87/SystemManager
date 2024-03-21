/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author under
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost";
    private static final String USER = "pooa2024";
    private static final String PASSWORD = "pooa2024";
    
    public void newUserTable(String newUsers) throws SQLException{
       try {
           Connection conn = DriverManager.getConnection( DB_URL, USER, PASSWORD);
          Statement stmt = conn.createStatement();
          stmt.execute("USE CA2;");
          stmt.execute("CREATE TABLE " + newUsers + " (username VARCHAR(25), password VARCHAR(25), role VARCHAR(25)); " );
           System.out.println("table sucessfully creted ");
           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    
}
