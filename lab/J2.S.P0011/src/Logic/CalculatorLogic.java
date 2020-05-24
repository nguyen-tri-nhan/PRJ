/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import GUI.MyCalculator;
import java.awt.Component;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author nguyentrinhan2000
 */
public class CalculatorLogic {

    private int lastOperator;
    //String Error = "Error";

    public int getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(int lastOperator) {
        this.lastOperator = lastOperator;
    }
    
    public double getValue(String value) {
        if (value.endsWith(".")) {
            value = value.replace(".", "");
        }
        return Double.parseDouble(value);
    }

    public double cal(double Memory, double Current, int Operator) {
        switch (Operator) {
            case 1:
                Memory += Current;
                lastOperator = 1;
                break;
            case 2:
                Memory -= Current;
                lastOperator = 2;
                break;
            case 3:
                Memory = Memory * Current;
                lastOperator = 3;
                break;
            case 4:
                Memory /= Current;
                lastOperator = 4;
                break;
            case 0:
                switch (lastOperator) {
                    case 1:
                        Memory += Current;
                        break;
                    case 2:
                        Memory -= Current;
                        break;
                    case 3:
                        Memory = Memory * Current;
                        break;
                    case 4:
                        Memory /= Current;
                        break;
                    default:
                        Memory = Current;
                        break;
                }
                break;
        }
        return Memory;
    }


}
