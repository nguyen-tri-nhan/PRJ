/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dto.Item;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ItemTableModel <E> extends AbstractTableModel{
    String[] header;
    int[] indexes;
    Vector<Item> data;

    public ItemTableModel(String[] headers, int[] indexes) {
        int i = 0;
        this.header = new String[headers.length];
        for(i = 0; i < headers.length; i++){
            this.header[i] = headers[i];
        }
        
        this.indexes = new int[indexes.length];
        for(i = 0; i < indexes.length; i++){
            this.indexes[i] = indexes[i];
        }
        this.data = new Vector<Item>();
    }

    public Vector<Item> getData() {
        return data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column){
        return (column >= 0 && column < header.length)? header[column]:"";
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        if(row <0 || row>= data.size() || column<0 || column >= header.length){
            return null;
        }
        Item sup = data.get(row);
        switch(indexes[column]){
            case 0: return sup.getItemCode();
            case 1: return sup.getItemName();
            case 2: return sup.getPrice();
            case 3: return sup.getSupplier();
            case 4: return sup.getUnit();
            case 5: return sup.isSupplying();
        }
        return null;
    }   
}
