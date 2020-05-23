/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import GUI.MyCalculator;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyentrinhan2000
 */
public class CalculatorLogic {

    int lastOperator;
    String Error = "Error";

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
            case 6:
                Memory = Memory * Current;
                lastOperator = 3;
                break;
            case 4:
                try {
                    if (Current == 0) {
                        throw new Exception("Error");
                    }
                    Memory /= Current;
                } catch (Exception e) {
                    Error = "Error";
                }
                lastOperator = 4;
            case 0:
                switch (lastOperator) {
                    case 1:
                        Memory += Current;
                        break;
                    case 2:
                        Memory -= Current;
                        break;
                    case 6:
                        Memory = Memory * Current;
                        break;
                    case 4:
                        try {
                            if (Current == 0) {
                                throw new Exception("Error");
                            }
                            Memory /= Current;
                        } catch (Exception e) {
                            Error = "Error";
                        }
                }
                break;
        }
        return Memory;
    }

//    public float cal(int operator, String valueGet) throws Exception {
//        if (!calcu) {
//            if (operator == Op) {
//                firstNum = getValue(valueGet);
//                System.out.println("first num"+firstNum + " operator: "+ operator);
//            } else {
//                secondNum = getValue(valueGet);
//                System.out.println("second num = " + secondNum);
//                System.out.println("first num again = " + firstNum);
//                if (operator == 1) {
//                    firstNum += secondNum;
//                    System.out.println("operator " + operator);
//                    return (float)firstNum;
//                }
//                if (operator == 2) {
//                    firstNum -= secondNum;
//                    return result();
//                }
//                if (operator == 3) {
//                    firstNum *= secondNum;
//                    return result();
//                }
//                if (operator == 4) {
//                    if (secondNum == 0) {
//                           
////                        jTextFieldDisplay.setText("Error");
////                        jButtonNumber0.setEnabled(false);
////                        jButtonNumber1.setEnabled(false);
////                        jButtonNumber2.setEnabled(false);
////                        jButtonNumber3.setEnabled(false);
////                        jButtonNumber4.setEnabled(false);
////                        jButtonNumber5.setEnabled(false);
////                        jButtonNumber6.setEnabled(false);
////                        jButtonNumber7.setEnabled(false);
////                        jButtonNumber8.setEnabled(false); 
////                        jButtonNumber9.setEnabled(false);
////                        jButtonOperatorPlus.setEnabled(false);
////                        jButtonOperatorMini.setEnabled(false);
////                        jButtonOperatorMul.setEnabled(false);
////                        jButtonOperatorDivide.setEnabled(false);
////                        jButtonOperatorEqual.setEnabled(false);
//                       return (float) 99999999999999.999999999999999999;
//                    } else {
//                        firstNum /= secondNum;
//                        return result();
//                    }
//                }
//            }
//            calcu = true;
//        }
//        return result();
//    }
//    public String pressNumber(String currenttext, String value) {
//        if (calcu || calcuM) {
//            calcu = false;
//            calcuM = false;
//            System.out.println("Calcu: " + calcu + "CalMem: "+ calcuM);
//            return "0";
//        }
//        if (currenttext.equals("0")) {
//            return value;
//        } else {
//            return currenttext + value;
//        }
//    }
}
