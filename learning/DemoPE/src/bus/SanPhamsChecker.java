/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dto.SanPhams;
import gui.SanPhamsGUI;
/**
 *
 * @author nguyentrinhan2000
 */
public class SanPhamsChecker {

    public static boolean checkData(SanPhams sanpham) {
        if (!sanpham.getId().matches("^P\\d{4}$")) {
            return false;
        }
        if (sanpham.getName().equals("") || sanpham.getName().length() > 50) {
            return false;
        }
        if (sanpham.getPrice() < 0) {
            return false;
        }
        return sanpham.getQuantity() >= 0;
    }
}
