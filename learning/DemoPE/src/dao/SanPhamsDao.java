/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.logging.Logger;
import conn.*;
import dto.SanPhams;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;

/**
 *
 * @author nguyentrinhan2000
 */
public class SanPhamsDao {
    DBAccess dataobj = null;

    public SanPhamsDao(DBAccess dataobj){
        this.dataobj = dataobj;
    }
    public SanPhamsDao() {
        try {
            DBAccess conn = new DBAccess();
            conn.openConnection(ConfigConnection.driver, ConfigConnection.url);
            this.dataobj = conn;
        } catch (Exception e) {
            Logger.getLogger(SanPhamsDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // get list SanPham
    public Vector<SanPhams> readAll() throws Exception{
        Vector<SanPhams> lst = new Vector<SanPhams>();
        String sql = "select * from SanPhams";       
        ResultSet rs = dataobj.excuteQuery(sql);
        while (rs.next()) {            
            String Id = rs.getNString("Id");
            String Name = rs.getNString("Name");
            int Price = rs.getInt("Price");
            int Quantity = rs.getInt("Quantity");
            boolean Available = rs.getBoolean("Available");
            SanPhams sanpham = new SanPhams(Id, Name, Price, Quantity, Available);
            lst.add(sanpham);
        }
        rs.close();
        return lst;
    }
    //get SP by ID
    public SanPhams read(String Id) throws Exception{
        String sql = "select * from Suppliers where SupCode = '"+Id+"'";
        ResultSet rs = dataobj.excuteQuery(sql);
        while (rs.next()) {
            String Name = rs.getNString("Name");
            int Price = rs.getInt("Price");
            int Quantity = rs.getInt("Quantity");
            boolean Available = rs.getBoolean("Available");
            SanPhams sanpham = new SanPhams(Id, Name, Price, Quantity, Available);
            return sanpham;
        }
        rs.close();return null;
    }
//    public void create(SanPhams sanpham) throws Exception{
//        String sql = "insert into SanPhams values('"+sanpham.getId()+"','"+sanpham.getName()+"','"+
//                sanpham.getPrice()+"','"+sanpham.getQuantity()+"',"+(sanpham.isAvailable()?1:0)+")";
//        dataobj.excuteQuery(sql);
//    }
//    public void update(SanPhams sanpham) throws Exception{
//        String sql = "Update SanPhams set Name='"+sanpham.getName()+"',Price='"+
//                sanpham.getPrice()+"',Quantity='"+sanpham.getQuantity()+"',Available="+(sanpham.isAvailable()?1:0)+" where Id='"+sanpham.getId()+"'";
//        dataobj.excuteQuery(sql);
//    }
    public void create(SanPhams sanphams) throws SQLException {
        String sql = "insert into SanPhams values('"+sanphams.getId() +"','"+ sanphams.getName() +"','"+ sanphams.getPrice() +"',"+ sanphams.getQuantity() +","+ (sanphams.isAvailable() ? 1 : 0) + ")";
        dataobj.executeUpdate(sql);
    }
    public void update(SanPhams sanphams) throws SQLException {
        String sql = "update SanPhams set Name='" + sanphams.getName() + "', Price='" + sanphams.getPrice() + "', Quantity=" + sanphams.getQuantity() + ", Available=" + (sanphams.isAvailable() ? 1 : 0) + " where Id='" + sanphams.getId() + "'";
        dataobj.executeUpdate(sql);
    }
    //delete
    public void delete(String Id) throws SQLException{
        String sql = "delete from SanPhams where Id='"+Id+"'";
        dataobj.executeUpdate(sql);
    }
}
