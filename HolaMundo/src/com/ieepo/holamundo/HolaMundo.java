/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieepo.holamundo;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ieepo.holamundo.db.ConnectionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author varguelles
 */
public class HolaMundo extends javax.swing.JApplet {

    /**
     * Initializes the applet HolaMundo
     */
    @Override
    public void init() {

        try {
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
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(HolaMundo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            //</editor-fold>
            
            /* Create and display the applet */
            
            java.awt.EventQueue.invokeAndWait(() -> {
                initComponents();
                setSize(new Dimension(1000, 600));
               
                
                
                ArrayList<String> empleados = new ArrayList<>();
                    
                ConnectionBD sql = new ConnectionBD();
                Connection cn = sql.conectar();
                PreparedStatement consulta;
                try {
                    consulta = cn.prepareStatement("SELECT * FROM empleados WHERE idct = ?");
                    consulta.setInt(1, 6155);
                    ResultSet resultado = consulta.executeQuery();

                    while(resultado.next()){
                        int idEmpleado;
                        String nombre;
                        String apPaterno;
                        String apMaterno;
                        String rfc;
                        int idct;

                        idEmpleado = resultado.getInt("idempleado");
                        nombre = resultado.getString("nombre").trim();
                        apPaterno = resultado.getString("apPaterno").trim();
                        apMaterno = resultado.getString("apMaterno").trim();
                        rfc = resultado.getString("rfc").trim();
                        idct = resultado.getInt("idct");


                        String e = idEmpleado + " " + nombre + " " + apPaterno + " " + apMaterno + " " + rfc + " " + idct;
                        empleados.add(e);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                }

                cmbEmpleados.removeAllItems();

                empleados.forEach((String empleado) -> {
                    cmbEmpleados.addItem(empleado);
                });
            });
            
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(HolaMundo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHolaMundo = new javax.swing.JButton();
        lbHola = new org.jdesktop.swingx.JXLabel();
        cmbEmpleados = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1000, 600));

        btnHolaMundo.setText("Hola mundo");
        btnHolaMundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHolaMundoActionPerformed(evt);
            }
        });

        lbHola.setText("                           ");

        cmbEmpleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHolaMundo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbHola, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(493, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHolaMundo)
                    .addComponent(lbHola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(cmbEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(392, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHolaMundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHolaMundoActionPerformed
        // TODO add your handling code here:
        lbHola.setText("Hola mundo");
    }//GEN-LAST:event_btnHolaMundoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHolaMundo;
    private javax.swing.JComboBox<String> cmbEmpleados;
    private org.jdesktop.swingx.JXLabel lbHola;
    // End of variables declaration//GEN-END:variables
}
