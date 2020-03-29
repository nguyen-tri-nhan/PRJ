/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dto.Supplier;

/**
 *
 * @author Administrator
 */
public class SupplierChecker {
    public static boolean checkData(Supplier supplier){
        if("".equals(supplier.getSupCode()) || supplier.getSupCode().length() > 5){
            return false;
        }
        if("".equals(supplier.getSupName()) || supplier.getSupName().length() > 50){
            return false;
        }
        if(supplier.getAddress().length() > 150){
            return false;
        }
        
        return true;
    }
}
