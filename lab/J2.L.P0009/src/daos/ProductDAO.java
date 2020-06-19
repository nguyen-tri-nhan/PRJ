/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ProductDTO;
import java.util.List;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nguyentrinhan2000
 */
public class ProductDAO implements Serializable{
    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;
    
    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    public List<ProductDTO> loadAllProduct() throws Exception {
        List<ProductDTO> list = null;
        ProductDTO dto = null;
        try {
            String sql = "USE ProductManagement;SELECT ProductID, ProductName, Price, Quantity FROM tblProduct";
            con = db.DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()){
                String productID = rs.getString("ProductID");
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                dto = new ProductDTO(productID, productName, price, quantity);
                list.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public ProductDTO findByProductID(String productID) throws Exception{
        ProductDTO dto = null;
        
        try {
            String sql = "SELECT ProductID, ProductName, Price, Quantity FROM tblProduct WHERE ProductID=?";
            con = db.DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            if (rs.next()){
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                dto = new ProductDTO(productID, productName, price, quantity);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        
        return dto;
    }
    
    public boolean insertProduct(ProductDTO dto) throws Exception{
        boolean check = false;
        
        try {
            String sql = "INSERT INTO tblProduct (ProductID, ProductName, Price, Quantity) VALUES(?,?,?,?)";
            con = db.DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getProductID());
            stm.setString(2, dto.getProductname());
            stm.setInt(3, dto.getPrice());
            stm.setInt(4, dto.getQuantity());
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        
        return check;
    }
}
