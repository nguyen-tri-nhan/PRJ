/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author nguyentrinhan2000
 */
public class LineChartPanel extends javax.swing.JPanel {

    int leftGap = 40;
    int topGap = 10;
    int rightGap = 10;
    int bottomGap = 40;
    int innerGap = 30;
    Color curreColor = Color.BLACK;
    DeviceWindow chartArea = null;
    int numOfScale = 4;

    /**
     * Creates new form LineChartPanel
     */
    public LineChartPanel() {
        initComponents();
    }

    public int getLeftGap() {
        return leftGap;
    }

    public void setLeftGap(int leftGap) {
        this.leftGap = leftGap;
    }

    public int getTopGap() {
        return topGap;
    }

    public void setTopGap(int topGap) {
        this.topGap = topGap;
    }

    public int getRightGap() {
        return rightGap;
    }

    public void setRightGap(int rightGap) {
        this.rightGap = rightGap;
    }

    public int getBottomGap() {
        return bottomGap;
    }

    public void setBottomGap(int bottomGap) {
        this.bottomGap = bottomGap;
    }

    public int getInnerGap() {
        return innerGap;
    }

    public void setInnerGap(int innerGap) {
        this.innerGap = innerGap;
    }

    public Color getCurreColor() {
        return curreColor;
    }

    public void setCurreColor(Color curreColor) {
        this.curreColor = curreColor;
    }

    public DeviceWindow getChartArea() {
        return chartArea;
    }

    public void setChartArea(DeviceWindow chartArea) {
        this.chartArea = chartArea;
    }

    public int getNumOfScale() {
        return numOfScale;
    }

    public void setNumOfScale(int numOfScale) {
        this.numOfScale = numOfScale;
    }

    void setupChartArea() {
        int left = leftGap + innerGap;
        int top = topGap + innerGap;
        int width = this.getWidth() - leftGap - rightGap - 2 * innerGap;
        int height = this.getHeight() - topGap - bottomGap - 2 * innerGap;
        try {
            this.chartArea = new DeviceWindow(left, top, width, height);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Device window parameters must be positive int");
        }
    }

    void drawAxes() {
        Graphics g = this.getGraphics();
        int arrowLength = 10, arrowWidth = 2;
        //draw horizontal axis
        int x1, y1, x2, y2;
        x1 = x2 = this.leftGap;
        y1 = this.getHeight() - this.bottomGap;
        y2 = this.topGap;
        g.drawLine(x1, y1, x2, y2);
        //draw arrow
        g.drawLine(x2 - arrowWidth, y2 + arrowLength, x2, y2);
        g.drawLine(x2 + arrowWidth, y2 + arrowLength, x2, y2);
        // draw vertical axis
        x1 = this.leftGap;
        x2 = this.getWidth() - this.rightGap;
        y1 = y2 = this.getHeight() - this.bottomGap;
        g.drawLine(x1, y1, x2, y2);
        //draw arrow
        g.drawLine(x2, y2, x2 - arrowLength, y2 - arrowWidth);
        g.drawLine(x2, y2, x2 - arrowLength, y2 + arrowWidth);
    }

    void drawLabels(String x_label, String y_label) {
        Graphics g = this.getGraphics();
        Font font = g.getFont();
        FontMetrics fm = g.getFontMetrics();
        int H = fm.getHeight();
        int Lx = fm.stringWidth(x_label);
        int Ly = fm.stringWidth(y_label);
        int x, y;
        // draw x-label
        x = this.leftGap + this.chartArea.width / 2 + Lx / 2;
        y = this.getHeight() - this.bottomGap + H + 10;
        g.drawString(x_label, x, y); // direction = horizontal
        //draw y-label
        x = this.leftGap - H / 2 - 10;
        y = this.topGap + this.getHeight() / 2 - Ly / 2;
        Graphics2D g2D = (Graphics2D) g;
        //rotate -PI/2 to draw text Vertically
        g2D.rotate(-Math.PI / 2, x, y);
        g2D.drawString(y_label, x, y);
    }

    //draw scaling label on horizontal axes
    // (x,y): pos on the horizontal axes
    private void drawX_LabelScale(Graphics g, String S, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int L = fm.stringWidth(S);
        g.drawString(S, x - L / 2, y + fm.getHeight() + 5);
    }

    //draw a scaling lable on the vertical axes
    //in axes, xAxix, YAxis are pos on the axis
    private void drawY_LableScale(Graphics g, String S, int xAxis, int yAxis) {
        FontMetrics fm = g.getFontMetrics();
        int L = fm.stringWidth(S);
        g.drawString(S, xAxis - L - 10, yAxis);
    }

    // draw scaling marks and real limit values on axes
    public void drawScale(RealPointList rList) {
        Graphics g = this.getGraphics();
        //draw scaling marks and limit values on horizontal axis
        int y = this.getHeight() - this.bottomGap;
        int dx = this.chartArea.width / (numOfScale - 1);
        int x = chartArea.left;
        double deltaX = (rList.maxX - rList.minX) / (numOfScale - 1);
        double xValue = rList.minX;
        for (int i = 0; i < numOfScale; i++) {
            g.drawLine(x, y, x, y + 5);
            if (i == 0 || i == numOfScale - 1) {
                this.drawX_LabelScale(g, "" + xValue, x, y);
            }
            x += dx;
            xValue += deltaX;
        }
        x = this.leftGap;
        y = chartArea.top + chartArea.height;
        int dy = this.chartArea.height / (numOfScale - 1);
        double yValue = rList.minY;
        double deltaY = (rList.maxY - rList.minY) / (numOfScale - 1);
        for (int i = 0; i < numOfScale; i++) {
            g.drawLine(x, y, x - 5, y);
            if (i == 0 || i == numOfScale - 1) {
                this.drawY_LableScale(g, "" + yValue, x, y);
            }
            y -= dy;
            yValue += deltaY;
        }
    }

    //draw line chart for a list using a known real ranges
    public void drawChart(RealPointList list, double minX, double minY, double maxX, double maxY) {
        RealWindow rWindow = null;
        double width = maxX - minX;
        double height = maxY - minY;
        try {
            //create a real window
            rWindow = new RealWindow(minX, minY, width, height);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Parameters of real window must be positive numbers");
        }
        if (rWindow != null) {
            //creata a mapping: real window -> chartArea
            RealToDeviceWindowMapping map = new RealToDeviceWindowMapping(rWindow, chartArea);
            DevicePointList pList = map.map(list);
            // draw line chart
            int n = pList.size(); // number of points of the list
            if (n > 1) {
                {
                    Graphics g = this.getGraphics();
                    Point p1 = pList.get(0); // 2 points for drawg a line
                    Point p2;
                    int i = 1;
                    while (i < n) {
                        p2 = pList.get(i);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                        p1 = p2;
                        i++;
                    }
                }
            }
        }
    }

    /**
     * Creates new form LineChartPanel
     */
//    public LineChartPanel() {
//        initComponents();
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
