/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import GUI.SimpleCalculator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.math.*;
import javax.swing.*;

/**
 *
 * @author lab003
 */
public class Calculator {

    SimpleCalculator c;
    BigDecimal Memory = new BigDecimal("0");
    BigDecimal result = new BigDecimal("0");
    BigDecimal Dnum = new BigDecimal("0");
    String operator = ""; 
    Boolean newNumber = true;

    public BigDecimal getMemory() {
        return Memory;
    }

    public void setMemory(BigDecimal Memory) {
        this.Memory = Memory;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    
    
    
    public Calculator(SimpleCalculator c) {
        this.c = c;
    }

    public String ButtonClear() {
        Dnum = new BigDecimal("0");
        result = new BigDecimal("0");
        operator = "";
        newNumber = true;
        return ("0");
    }
    
    public String addDigit(String text, String s) {
        // chữ số đầu vào sẽ thay thế văn bản hiện tại
        if (newNumber) {
            //*sau toán tử = kết quả sẽ được đặt lại để thao tác tiếp theo không lấy kết quả hoạt động trước đó
            if (operator.equals("=")) {
                result = new BigDecimal("0");
            }
            newNumber = false;
            return (FixNumber(new BigDecimal(s)));
        } else 
        //văn bản hiện tại là 0, chữ số đầu tiên sẽ thay thế nó
         if ((text).equals("0")) {
                return (FixNumber(new BigDecimal("s")));
            } else 
        //thêm 1 chữ số vào cuối văn bản
            {
                return (text + s);
            }
    }
    
    public String Calculate(String text, String currentOp) {
        String output = text;
        if (!newNumber) {
            BigDecimal num = new BigDecimal(text);
            switch (operator) {
                case "+":
                    result = result.add(num);
                    break;
                case "-":
                    result = result.subtract(num);
                    break;
                case "*":
                    result = result.multiply(num);
                    break;
                case "/":
                    if (num.equals(new BigDecimal("0"))) {
                        output = "Can not divided by zero";
                    } else {
                        result = result.divide(num, 12, RoundingMode.HALF_EVEN);
                    }
                    break;
                default:
                    result = new BigDecimal(text);
                    break;
            }
            if (!output.equals("Can not divided by zero")) {
                output = FixNumber(result);
            }
        }
        //lưu toán tử mới để tính tiếp theo
        operator = currentOp;
        newNumber = true;
        return output;
    }
    public void processMemory(String text, String proccessOP){
        switch (proccessOP){
            case "M+":
                Memory = Memory.add(result);
                break;
            case "M-":
                Memory = Memory.subtract(result);
                break;
        }
    }
    public String FixNumber(BigDecimal currentNum) {
        String s = currentNum.toString();
        //đi qua s để tìm xem nó có dấu chấm không
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                String newS = "";
                int newL = s.length();
                //đi qua s để xóa tất cả chữ số 0 cuối cùng sau dấu chấm
                for (int j = s.length() - 1; j > 1; j--) 
                //trong khi chữ số cuối cùng vẫn là 0
                {
                    if (s.charAt(j) == '0') 
                //cập nhật độ dài mới của s
                    {
                        newL = j;
                    } 
                //chữ số tiếp theo là có thật
                    else {
                        break;
                    }
                }
                //tất cả chữ số sau dấu chấm là 0 . vì vậy dấu chấm là vô ích
                if (s.charAt(newL - 1) == '.') {
                    newL--;
                }
                //đi qua s để lưu chuỗi mới
                for (int j = 0; j < newL; j++) {
                    newS = newS + s.charAt(j);
                }
                s = newS;
                break;
            }
        }
        return s;
    }
    
}
