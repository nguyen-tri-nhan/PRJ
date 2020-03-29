
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguyentrinhan2000
 */
public class Test {
    public static void main(String[] args){
        try {
            Database db = new Database();
            Connection cn = db.openConnection();
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Items");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2));
            }
            cn.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
