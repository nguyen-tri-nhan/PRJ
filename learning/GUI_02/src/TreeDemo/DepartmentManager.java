/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Iterator;
import java.util.Enumeration;
import java.io.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTree;

/**
 *
 * @author Admin
 */
public class DepartmentManager extends javax.swing.JFrame {

    String filename = "employee2.txt";
    Department dp = new Department();
    DefaultMutableTreeNode root = null;
    DefaultMutableTreeNode curDepNode = null;
    DefaultMutableTreeNode curEmpNode = null;
    boolean addNewDep = false;
    boolean addNewEmp = false;

    /**
     * Creates new form DepartmentManager
     */
    public DepartmentManager() {
        initComponents();
        //this.setSize(520, 380);
        //JTree tree = new JTree();
        root = (DefaultMutableTreeNode) (this.tree.getModel().getRoot());
        loadData();
        TreePath path = new TreePath(root);
        tree.expandPath(path);
    }

    private void loadData() {
        String S = "";
        StringTokenizer stk;
        try {
            FileReader f = new FileReader(filename);
            BufferedReader bf = new BufferedReader(f);
            while ((S = bf.readLine()) != null) {
                S = S.trim();
                boolean isDept = (S.charAt(S.length() - 1) == ':');
                stk = new StringTokenizer(S, "-:,");
                String code = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                if (isDept) {
                    curDepNode = new DefaultMutableTreeNode(new Department(code, name));
                    root.add(curDepNode);
                } else {
                    int salary = Integer.parseInt(stk.nextToken().trim());
                    curEmpNode = new DefaultMutableTreeNode(new Employee(code, name, salary));
                    curDepNode.add(curEmpNode);
                }
            }
            bf.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewDeptAndEmp() {
        Department curDep = null;
        Employee curEmp = null;
        if (curDepNode != null) {
            curDep = (Department) (curDepNode.getUserObject());
        }
        this.txtDepCode.setText(curDep != null ? curDep.getDepCode() : "");
        this.txtDepName.setText(curDep != null ? curDep.getDepName() : "");
        this.txtEmpCode.setText(curEmp != null ? curEmp.getEmpCode() : "");
        this.txtEmpName.setText(curEmp != null ? curEmp.getEmpName() : "");
        this.txtSalary.setText("" + (curEmp != null ? curEmp.getSalary() : ""));
        this.txtDepCode.setEditable(false);
        this.txtEmpCode.setEditable(false);
    }

    private boolean validDepDetails() {
        String s = "";
        if (addNewDep == true) {
            s = this.txtDepCode.getText().trim().toUpperCase();
            this.txtDepCode.setText(s);
            if (!s.matches("^E\\d{3}$")) {
                JOptionPane.showMessageDialog(this, "Code format: Exxx(x : numbers");
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                Vector v = (Vector) (root.getChildAt(i));
                if (s.equals((String) (v.get(0)))) {
                    JOptionPane.showMessageDialog(this, "Code duplicated");
                    txtDepCode.requestFocus();
                    return false;
                }
            }
        }
        s = this.txtDepName.getText().trim();
        if ((s.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Name is required.");
            return false;
        }
        return true;
    }
    
    private boolean validEmpDetails() {
        String s = "";
        if (addNewEmp == true) {
            s = this.txtEmpCode.getText().trim().toUpperCase();
            this.txtEmpCode.setText(s);
            if (!s.matches("^E\\d{3}$")) {
                JOptionPane.showMessageDialog(this, "Code format: Exxx(x : numbers");
                return false;
            }
            /*for (int i = 0; i < root.getChildCount(); i++) {
                Vector v = (Vector) (root.getChildAt(i));
                if (s.equals((String) (v.get(0)))) {
                    JOptionPane.showMessageDialog(this, "Code duplicated");
                    txtEmpCode.requestFocus();
                    return false;
                }
            }*/
        }
        s = this.txtEmpName.getText().trim();
        if ((s.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Name is required.");
            return false;
        }
        s = this.txtSalary.getText().trim();
        if (!s.matches("^\\d+$")) {
            JOptionPane.showMessageDialog(this, "Salary is an integer.");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDepCode = new javax.swing.JTextField();
        txtDepName = new javax.swing.JTextField();
        btnDepNew = new javax.swing.JButton();
        btnDepRemove = new javax.swing.JButton();
        btnDepSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmpCode = new javax.swing.JTextField();
        txtEmpName = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        btnEmpNew = new javax.swing.JButton();
        btnEmpRemove = new javax.swing.JButton();
        btnEmpSave = new javax.swing.JButton();
        btnSaveFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Departments");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tree);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setText("Dept. code:");

        jLabel2.setText("Dept. name:");

        btnDepNew.setText("New");
        btnDepNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepNewActionPerformed(evt);
            }
        });

        btnDepRemove.setText("Remove");
        btnDepRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepRemoveActionPerformed(evt);
            }
        });

        btnDepSave.setText("Save");
        btnDepSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDepNew, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDepRemove)
                        .addGap(18, 18, 18)
                        .addComponent(btnDepSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDepCode, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDepCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDepName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDepRemove)
                    .addComponent(btnDepSave)
                    .addComponent(btnDepNew))
                .addGap(28, 28, 28))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Emp. code:");

        jLabel4.setText("Emp. name:");

        jLabel5.setText("Salary:");

        btnEmpNew.setText("New");
        btnEmpNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpNewActionPerformed(evt);
            }
        });

        btnEmpRemove.setText("Remove");
        btnEmpRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpRemoveActionPerformed(evt);
            }
        });

        btnEmpSave.setText("Save");
        btnEmpSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtEmpName)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(58, 58, 58)
                                .addComponent(txtEmpCode, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEmpNew, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEmpRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEmpSave, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmpCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmpNew)
                    .addComponent(btnEmpRemove)
                    .addComponent(btnEmpSave))
                .addGap(23, 23, 23))
        );

        btnSaveFile.setText("Save to file");
        btnSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveFile)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
        // TODO add your handling code here:
        tree.cancelEditing();
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            return;
        }
        DefaultMutableTreeNode selectedNode = null;
        selectedNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
        Object selectedObj = selectedNode.getUserObject();
        if (selectedNode == root) {
            this.curDepNode = this.curEmpNode = null;
        } else {
            if (selectedObj instanceof Department) {
                this.curDepNode = selectedNode;
                this.curEmpNode = null;
            } else if (selectedObj instanceof Employee) {
                curEmpNode = selectedNode;
                curDepNode = (DefaultMutableTreeNode) (selectedNode.getParent());
            }
        }
        viewDeptAndEmp();
    }//GEN-LAST:event_treeMouseClicked

    private void btnDepNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepNewActionPerformed
        // TODO add your handling code here:
        this.addNewDep = true;
        this.txtDepCode.setText("");
        this.txtDepName.setText("");
        this.txtEmpCode.setText("");
        this.txtEmpName.setText("");
        this.txtSalary.setText("");
        this.txtDepCode.setEditable(true);
        this.txtDepCode.requestFocus();
    }//GEN-LAST:event_btnDepNewActionPerformed

    private void btnDepRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepRemoveActionPerformed
        // TODO add your handling code here:
        if (this.curDepNode.getChildCount() > 0) {
            String msg = "Remove all employees before deleting a department.";
            JOptionPane.showMessageDialog(this, msg);
        } else {
            int response = JOptionPane.showConfirmDialog(this, "Delete this department- OK?");
            if (response == JOptionPane.OK_OPTION) {
                root.remove(this.curDepNode);
                tree.updateUI();
            }
        }
    }//GEN-LAST:event_btnDepRemoveActionPerformed

    private void btnDepSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepSaveActionPerformed
        // TODO add your handling code here:
        String code = this.txtDepCode.getText().trim().toUpperCase();
        txtDepCode.setText(code);
        String name = this.txtDepName.getText().trim();
        txtDepName.setText(name);
        if (!validDepDetails()) {
            return;
        }
        if (addNewDep == true) {
            Department newDep = new Department(code, name);
            root.add(new DefaultMutableTreeNode(newDep));
        } else {
            ((Department) curDepNode.getUserObject()).setDepName(name);
        }
        this.tree.updateUI();
        this.addNewDep = false;
    }//GEN-LAST:event_btnDepSaveActionPerformed

    private void btnEmpNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpNewActionPerformed
        // TODO add your handling code here:
        this.addNewEmp = true;
        this.txtEmpCode.setText("");
        this.txtEmpName.setText("");
        this.txtSalary.setText("");
        this.txtEmpCode.setEditable(true);
        this.txtEmpCode.requestFocus();
    }//GEN-LAST:event_btnEmpNewActionPerformed

    private void btnEmpRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpRemoveActionPerformed
        // TODO add your handling code here:
        if (this.curEmpNode != null) {
            int response = JOptionPane.showConfirmDialog(this, "Delete this employee- OK?");
            if (response == JOptionPane.OK_OPTION) {
                curDepNode.remove(this.curEmpNode);
                tree.updateUI();
            }
        }
    }//GEN-LAST:event_btnEmpRemoveActionPerformed

    private void btnEmpSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpSaveActionPerformed
        // TODO add your handling code here:
        String code = this.txtEmpCode.getText().trim().toUpperCase();
        txtEmpCode.setText(code);
        String name = this.txtEmpName.getText().trim();
        txtEmpName.setText(name);
        String salaryStr = this.txtSalary.getText().trim();
        txtSalary.setText(salaryStr);
        int sal = Integer.parseInt(salaryStr);
        if (!validEmpDetails()) {
            return;
        }
        if (addNewEmp == true) {
            Employee newEmp = new Employee(code, name, sal);
            root.add(new DefaultMutableTreeNode(newEmp));
        } else {
            Employee emp = (Employee) (curEmpNode.getUserObject());
            emp.setEmpName(name);
            emp.setSalary(sal);
        }
        this.tree.updateUI();
        this.addNewEmp = false;
    }//GEN-LAST:event_btnEmpSaveActionPerformed

    private void btnSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveFileActionPerformed
        // TODO add your handling code here:
        if (root.getChildCount() == 0) {
            return;
        }
        String S;
        try {
            FileWriter f = new FileWriter(filename);
            PrintWriter pf = new PrintWriter(f);
            Enumeration depts = root.children();
            while (depts.hasMoreElements()) {
                DefaultMutableTreeNode depNode = (DefaultMutableTreeNode)depts.nextElement();
                Department dept = (Department)(depNode.getUserObject());
                S = dept.getDepCode() + "-" + dept.getDepName() + ":";
                pf.println(S);
                Enumeration emps = depNode.children();
                while(emps.hasMoreElements()) {
                    DefaultMutableTreeNode empNode = (DefaultMutableTreeNode)emps.nextElement();
                    Employee emp = (Employee) (empNode.getUserObject());
                    S = emp.getEmpCode() + "," + emp.getEmpName() + "," + emp.getSalary();
                    pf.println(S);
                }
            }
            pf.close();
            f.close();
            JOptionPane.showMessageDialog(this, "Data are saved to the file " + filename);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnSaveFileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDepNew;
    private javax.swing.JButton btnDepRemove;
    private javax.swing.JButton btnDepSave;
    private javax.swing.JButton btnEmpNew;
    private javax.swing.JButton btnEmpRemove;
    private javax.swing.JButton btnEmpSave;
    private javax.swing.JButton btnSaveFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree tree;
    private javax.swing.JTextField txtDepCode;
    private javax.swing.JTextField txtDepName;
    private javax.swing.JTextField txtEmpCode;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtSalary;
    // End of variables declaration//GEN-END:variables
}
