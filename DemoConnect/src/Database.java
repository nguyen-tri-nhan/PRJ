/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguyentrinhan2000
 */
import java.sql.*;
class Database {
    Connection openConnection() {
        //String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        String user = "new";
        String pwd = "123456789";
        Connection c = null;
        String url = "jdbc:sqlserver://LAPTOP-OR608870:1433;database=ItemDB";
        try {
            //Class.forName(driver);
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            c = DriverManager.getConnection(url,user,pwd);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        return c;
    }
}

