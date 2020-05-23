/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

/**
 *
 * @author nguyentrinhan2000
 */
public class RealPoint implements Comparable<RealPoint>{

    public double x, y;

    public RealPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(RealPoint p) {
        int result = 0;
        if (this.x > p.x) result = 1;
        if (this.x < p.x) result = -1;
        return result;
    }
    
}
