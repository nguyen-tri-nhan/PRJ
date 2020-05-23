/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import conn.DBAccess;
import dao.ItemDao;
import dao.SupplierDao;
import dto.Supplier;

/**
 *
 * @author Administrator
 */
public class TestProgram {
    public static void main (String []args){
        
        System.out.println("Hello World!");
        
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=ItemDB;user=new;password=123456789;";
        
        // Test Database Connection
        try{
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        // Test get all Supplier
        try{
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            SupplierDao supDao = new SupplierDao(testCon);
            System.out.println(supDao.readAll());
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        // Test get Supplier with SupCode = NA
        try{
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            SupplierDao supDao = new SupplierDao(testCon);
            System.out.println(supDao.read("NA"));
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        // Test create Supplier
        try{
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            SupplierDao supDao = new SupplierDao(testCon);
            Supplier supplier = new Supplier("CO","Covid-19", "All over the world", true);
            supDao.create(supplier);
            System.out.println(supDao.readAll());
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        // Test update Supplier
        try{
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            SupplierDao supDao = new SupplierDao(testCon);
            Supplier supplier = new Supplier("CO","Covid-19", "All over the world", false);
            supDao.update(supplier);
            System.out.println(supDao.readAll());
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        // Test delete Supplier
        try{
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            SupplierDao supDao = new SupplierDao(testCon);
            supDao.delete("CO");
            System.out.println(supDao.readAll());
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
         // Test read Item
        try{           
            DBAccess testCon1 = new DBAccess();
            testCon1.openConnection(driver, url);
            SupplierDao supDao = new SupplierDao(testCon1);
            
            DBAccess testCon = new DBAccess();
            testCon.openConnection(driver, url);
            ItemDao itemDao = new ItemDao(testCon, supDao);
            System.out.println(itemDao.read("E0001"));
            testCon.closeConnection();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
