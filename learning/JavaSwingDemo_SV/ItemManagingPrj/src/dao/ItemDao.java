/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conn.ConfigConnection;
import conn.DBAccess;
import dto.Item;
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
public class ItemDao {
    DBAccess dbObj;
    SupplierDao supplierDao;
    
    public ItemDao(DBAccess dbObj, SupplierDao supplierDao) {
        this.dbObj = dbObj;
        this.supplierDao = supplierDao;
    }
    
    public ItemDao() {
        
        try {
            DBAccess conn = new DBAccess();
            conn.openConnection(ConfigConnection.driver, ConfigConnection.url);
            this.dbObj = conn;           
            supplierDao = new SupplierDao();
        } catch (Exception ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     // Get list Items   
    public Vector<Item> readAll() throws Exception{
        Vector<Item> lst = new Vector<>();     
        String sql = "select * from Items";       
        ResultSet rs = dbObj.excuteQuery(sql);
        
        while(rs.next()){
            String itemCode = rs.getNString("itemCode");
            String itemName = rs.getNString("itemName");
            String supCode = rs.getNString("supCode");
            Supplier supplier = supplierDao.read(supCode);
            String unit = rs.getNString("unit");
            int price = rs.getInt("price");
            boolean supplying = rs.getBoolean("supplying");
            
            Item item = new Item(itemCode, itemName, supplier, unit, price, supplying);
            lst.add(item);
        }       
        rs.close();      
        return lst;
    }
    
     // Get list Item   
    public Item read(String itemCode) throws Exception{    
        String sql = "select * from Items where itemCode='"+itemCode+"'";       
        ResultSet rs = dbObj.excuteQuery(sql);
        
        while(rs.next()){
            String itemName = rs.getNString("itemName");
            String supCode = rs.getNString("supCode");
            Supplier supplier = supplierDao.read(supCode);
            String unit = rs.getNString("unit");
            int price = rs.getInt("price");
            boolean supplying = rs.getBoolean("supplying");
            
            Item item = new Item(itemCode, itemName, supplier, unit, price, supplying);
            
            return item;
        }       
        rs.close();      
        return null;
    }
    
    // Create item    
    public void create(Item item) throws SQLException{
        String sql = "insert into Items values('"+item.getItemCode()+"','"+item.getItemName()+"','"+
                item.getSupplier().getSupCode()+"','"+item.getUnit()+"',"+item.getPrice()+","+(item.isSupplying()?1:0)+")";
        dbObj.executeUpdate(sql);
    }
    
    // Update item
    public void update(Item item) throws SQLException{
        String sql = "update Items set itemName='"+item.getItemName()+"', supCode='"+item.getSupplier().getSupCode()+
                "', unit='"+item.getUnit()+"', price="+item.getPrice()+
                ", supplying="+(item.isSupplying()?1:0)+" where itemCode='"+item.getItemCode()+"'";
        dbObj.executeUpdate(sql);
    }
    
    // Delete item
    public void delete(String itemCode) throws SQLException{
        String sql = "delete from Items where itemCode='"+itemCode+"'";
        dbObj.executeUpdate(sql);
    }
}
