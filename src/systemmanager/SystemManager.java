/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package systemmanager;

import java.sql.SQLException;
import static systemmanager.MainSystemMenu.displayMainMenu;
import static systemmanager.Reports.CourseReport.generateCourseReport;

/**
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
        //displayMainMenu(); //call main menu for program
        generateCourseReport();
    }

}
