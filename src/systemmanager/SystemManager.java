/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package systemmanager;

import systemmanager.Reports.StudentsReport;
import java.sql.SQLException;
import systemmanager.Reports.CourseReport;

/** link to github  https://github.com/ckell87/SystemManager.git
 *
 * @author under
 */
public class SystemManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        //dc.dropTable("newUsers"); 
        //dc.renameTable();
        //dc.alterTable();
        MainSystemMenu.displayMainMenu();

    }

}
