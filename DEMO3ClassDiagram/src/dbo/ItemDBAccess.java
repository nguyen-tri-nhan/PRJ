/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author nguyentrinhan2000
 */
public class ItemDBAccess extends DBAccess {

    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=ItemDB;user=new;password=123456789";

    public ItemDBAccess() {
        super();
        connectDB(driver, url);
    }
}
