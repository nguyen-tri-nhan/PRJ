/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dbo.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nguyentrinhan2000
 */
public class ItemFullModel extends AbstractTableModel {

    Items items = null;

    public ItemFullModel(Items items) {
        this.items = items;
    }

    public Items getItems() {
        return items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        String columnName = "";
        switch (column) {
            case 0:
                columnName = "Code";
                break;
            case 1:
                columnName = "Name";
                break;
            case 2:
                columnName = "Supplier";
                break;
            case 3:
                columnName = "Unit";
                break;
            case 4:
                columnName = "Price";
                break;
            case 5:
                columnName = "Supply";
                break;
        }
        return columnName;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Item item = items.get(row);
        Object obj = null;
        switch (column) {
            case 0:
                obj = item.getItemCode();
                break;
            case 1:
                obj = item.getItemName();
                break;
            case 2:
                obj = item.getSupplier().getSupCode();
                break;
            case 3:
                obj = item.getUnit();
                break;
            case 4:
                obj = item.getPrice();
                break;
            case 5:
                obj = item.isSupplying();
                break;
        }
        return obj;
    }

}
