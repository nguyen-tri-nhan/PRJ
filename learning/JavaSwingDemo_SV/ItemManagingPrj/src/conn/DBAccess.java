/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class DBAccess {
    
    Connection con = null;
    Statement stmt = null;

    public DBAccess() {
    }
    
    public void openConnection(String driver, String url) throws Exception{
        Class.forName(driver); // Load driver
        con = DriverManager.getConnection(url); // Connect to database
        stmt = con.createStatement();
        System.out.println("Open connection successfully");
    }
    
    public void closeConnection() throws SQLException{
        if(con != null){
            stmt.close();
            con.close();
            System.out.println("Close connection successfully");
        }
        else{
            System.out.println("There is not any available connection");
        }
    }
    
    // Select
    public ResultSet excuteQuery(String selectSql) throws SQLException{
        if(con == null){
            return null;
        }
        return stmt.executeQuery(selectSql);
    }
    
    // Insert, Update, Delete
    public int executeUpdate(String updateSql) throws SQLException{
        if(con == null){
            return 0;
        }
        return stmt.executeUpdate(updateSql);
    }
    
    
}
