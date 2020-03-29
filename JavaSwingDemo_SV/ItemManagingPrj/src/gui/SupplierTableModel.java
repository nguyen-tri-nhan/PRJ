/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dto.Supplier;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class SupplierTableModel <E> extends AbstractTableModel{
    String[] header;
    int[] indexes;
    Vector<Supplier> data;

    public SupplierTableModel(String[] headers, int[] indexes) {
        int i = 0;
        this.header = new String[headers.length];
        for(i = 0; i < headers.length; i++){
            this.header[i] = headers[i];
        }
        
        this.indexes = new int[indexes.length];
        for(i = 0; i < indexes.length; i++){
            this.indexes[i] = indexes[i];
        }
        this.data = new Vector<Supplier>();
    }

    public Vector<Supplier> getData() {
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
        Supplier sup = data.get(row);
        switch(indexes[column]){
            case 0: return sup.getSupCode();
            case 1: return sup.getSupName();
            case 2: return sup.getAddress();
            case 3: return sup.isColloborating();
        }
        return null;
    }   
}
