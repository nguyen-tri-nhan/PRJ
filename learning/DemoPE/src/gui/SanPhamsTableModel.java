/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Vector;
import dto.SanPhams;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author nguyentrinhan2000
 */
public class SanPhamsTableModel<E> extends AbstractTableModel {

    String[] header;
    int[] indexes;
    Vector<SanPhams> data;

    public SanPhamsTableModel(String[] header, int[] indexes) {
        int i = 0;
        this.header = new String[header.length];
        for (i = 0; i < header.length; i++) {
            this.header[i] = header[i];
        }
        this.indexes = new int[indexes.length];
        for(i = 0; i < indexes.length; i++){
            this.indexes[i] = indexes[i];
        }
        this.data = new Vector<SanPhams>();
    }

    public Vector<SanPhams> getData() {
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
    public String getColumnName(int column) {
        return (column >= 0 && column < header.length) ? header[column] : "";
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row <0 || row>= data.size() || column<0 || column >= header.length){
            return null;
        }
        SanPhams sp = data.get(row);
        switch(indexes[column]){
            case 0: return sp.getId();
            case 1: return sp.getName();
            case 2: return sp.getPrice();
            case 3: return sp.getQuantity();
            case 4: return sp.isAvailable();
        }
        return null;
    }

}
