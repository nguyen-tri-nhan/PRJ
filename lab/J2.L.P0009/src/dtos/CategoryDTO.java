/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author nguyentrinhan2000
 */
public class CategoryDTO {
    String categoryID, categoryname;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryID, String categoryname) {
        this.categoryID = categoryID;
        this.categoryname = categoryname;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    
}
