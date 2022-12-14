/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oodj_assignment;

import javax.swing.JOptionPane;

/**
 *
 * @author HAO
 */
public class AddItem extends javax.swing.JFrame {

    MenuManagement objMM = new MenuManagement();
    /**
     * Creates new form AddItem
     */
    public AddItem() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        priceCbBx = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbBxFoodType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFldItemName = new javax.swing.JTextField();
        btnExit2 = new javax.swing.JButton();
        btnAddItem = new javax.swing.JButton();

        btnExit1.setBackground(new java.awt.Color(254, 120, 83));
        btnExit1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnExit1.setForeground(new java.awt.Color(242, 242, 242));
        btnExit1.setText("EXIT");
        btnExit1.setAlignmentY(0.0F);
        btnExit1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnExit1.setBorderPainted(false);
        btnExit1.setIconTextGap(30);
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 120, 83));
        jLabel1.setText("Add Item");

        priceCbBx.setMaximumRowCount(200);
        priceCbBx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));
        priceCbBx.setSelectedIndex(-1);
        priceCbBx.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Set price  : RM");
        jLabel3.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Item Type :");

        cbBxFoodType.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbBxFoodType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Western Food", "Local", "Beverage" }));
        cbBxFoodType.setSelectedIndex(-1);
        cbBxFoodType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBxFoodTypeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Item Name :");

        txtFldItemName.setEnabled(false);

        btnExit2.setBackground(new java.awt.Color(254, 120, 83));
        btnExit2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnExit2.setForeground(new java.awt.Color(242, 242, 242));
        btnExit2.setText("EXIT");
        btnExit2.setAlignmentY(0.0F);
        btnExit2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnExit2.setBorderPainted(false);
        btnExit2.setIconTextGap(30);
        btnExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit2ActionPerformed(evt);
            }
        });

        btnAddItem.setBackground(new java.awt.Color(204, 255, 204));
        btnAddItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddItem.setForeground(new java.awt.Color(102, 102, 102));
        btnAddItem.setText("Add Item");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnExit2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbBxFoodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(priceCbBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFldItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 164, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbBxFoodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtFldItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(priceCbBx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)
                        .addComponent(btnExit2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new UpdateMenu().setVisible(true);
    }//GEN-LAST:event_btnExit1ActionPerformed

    private void btnExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new UpdateMenu().setVisible(true);
    }//GEN-LAST:event_btnExit2ActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        int flag=0;
        if(objMM.verifyAlphabet(txtFldItemName) && !txtFldItemName.getText().trim().equals("")){
        flag++;
        }
        else
            JOptionPane.showMessageDialog(null,"Please enter a valid Item Name!","Warning",JOptionPane.WARNING_MESSAGE);
        if (cbBxFoodType.getSelectedIndex() != -1) {
            flag++;
        }
        else
            JOptionPane.showMessageDialog(null,"Please choose a food type!","Warning",JOptionPane.WARNING_MESSAGE);
        if (priceCbBx.getSelectedIndex() != -1) {
            flag++;
        }
        else
            JOptionPane.showMessageDialog(null,"Please set a price!","Warning",JOptionPane.WARNING_MESSAGE);
        if (flag ==3) {
            int reply =JOptionPane.showConfirmDialog(null,"Do u want to add " + txtFldItemName.getText()+ " of RM"+priceCbBx.getSelectedItem().toString()+
                    " to the "+ cbBxFoodType.getSelectedItem()+" menu?","Confirmation",JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION){
            objMM.addItem_WriteToMenuFile(cbBxFoodType, txtFldItemName, priceCbBx);
            JOptionPane.showMessageDialog(null,txtFldItemName.getText()+" has been successfully added to the " + cbBxFoodType.getSelectedItem()+" menu!");
            this.dispose();
            new UpdateMenu().setVisible(true);
            }
        } 
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void cbBxFoodTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBxFoodTypeActionPerformed
    txtFldItemName.setEnabled(Boolean.TRUE); 
    priceCbBx.setEnabled(Boolean.TRUE);
    priceCbBx.setSelectedIndex(-1);
    }//GEN-LAST:event_cbBxFoodTypeActionPerformed

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
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnExit2;
    private javax.swing.JComboBox<String> cbBxFoodType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> priceCbBx;
    private javax.swing.JTextField txtFldItemName;
    // End of variables declaration//GEN-END:variables
}
