/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conn.ConfigConnection;
import conn.DBAccess;
import dto.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SupplierDao {
    
    DBAccess dbObj = null;
    
    public SupplierDao(DBAccess dbObj) {
        this.dbObj = dbObj;
    }

    public SupplierDao() {
        try {
            DBAccess conn = new DBAccess();
            conn.openConnection(ConfigConnection.driver, ConfigConnection.url);
            this.dbObj = conn;
            
        } catch (Exception ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    // Get list supplier   
    public Vector<Supplier> readAll() throws Exception{
        Vector<Supplier> lst = new Vector<>();     
        String sql = "select * from Suppliers";       
        ResultSet rs = dbObj.excuteQuery(sql);
        
        while(rs.next()){
            String supCode = rs.getNString("SupCode");
            String supName = rs.getNString("SupName");
            String address = rs.getNString("Address");
            boolean colloborating = rs.getBoolean("colloborating");
            
            Supplier supplier = new Supplier(supCode, supName, address, colloborating);
            lst.add(supplier);
        }       
        rs.close();      
        return lst;
    }
    
    // Get supplier with id
    public Supplier read(String supCode) throws Exception{
        
        String sql = "select * from Suppliers where SupCode = '"+supCode+"'";        
        ResultSet rs = dbObj.excuteQuery(sql);       
        while(rs.next()){
            String supName = rs.getNString("SupName");
            String address = rs.getNString("Address");
            boolean colloborating = rs.getBoolean("colloborating");
            
            Supplier supplier = new Supplier(supCode, supName, address, colloborating);
            return supplier;
        }       
        rs.close();
        return null;
    }
    
    // Create supplier    
    public void create(Supplier supplier) throws SQLException{
        String sql = "insert into Suppliers values('"+supplier.getSupCode()+"','"+supplier.getSupName()+"','"+
                supplier.getAddress()+"',"+(supplier.isColloborating()?1:0)+")";
        dbObj.executeUpdate(sql);
    }
    
    // Update supplier
    public void update(Supplier supplier) throws SQLException{
        String sql = "update Suppliers set SupName='"+supplier.getSupName()+"', Address='"+supplier.getAddress()+
                "', colloborating="+(supplier.isColloborating()?1:0)+" where SupCode='"+supplier.getSupCode()+"'";
        dbObj.executeUpdate(sql);
    }
    
    // Delete supplier
    public void delete(String supCode) throws SQLException{
        String sql = "delete from Suppliers where SupCode='"+supCode+"'";
        dbObj.executeUpdate(sql);
    }
}
