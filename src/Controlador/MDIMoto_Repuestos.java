/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package Controlador;
import Vista.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;

public class MDIMoto_Repuestos extends javax.swing.JFrame {

    private JInternalFrameCliente Clientes;
    private JInternalFrameProductos Productos;
    private JInternalFrameProveedor Proveedor;
    private JInternalFrameMarca Marca;
    private JInternalFrameVenta Venta;
    private JInternalFrameDetalles_Venta Detalle_V;
    private  JInternalFrameDetalle_Compra Compras;
    private NewJInternalFrameCompra Compra;
    private JInternalFrameReportes Reporte;
      
    
    /**
     * Creates new form NewMDIMoto_Repuestos
     */
    public MDIMoto_Repuestos() throws SQLException {
        initComponents();
        
        
        setTitle("Moto Repuestos Leyton");
        
        Clientes= new JInternalFrameCliente();
        jDesktopPane1.add(Clientes);   
        
        Productos = new JInternalFrameProductos();
        jDesktopPane1.add(Productos);
        
        
        Proveedor=new JInternalFrameProveedor();
        jDesktopPane1.add(Proveedor);
        
        Marca=new JInternalFrameMarca();
        jDesktopPane1.add(Marca);
        
        Venta= new JInternalFrameVenta();
        jDesktopPane1.add(Venta);
        
        Detalle_V = new JInternalFrameDetalles_Venta();
        jDesktopPane1.add(Detalle_V);
        
        Compras = new JInternalFrameDetalle_Compra();
        jDesktopPane1.add(Compras);
        
        Compra = new NewJInternalFrameCompra();
        jDesktopPane1.add(Compra);
        
        Reporte =new JInternalFrameReportes();
        jDesktopPane1.add(Reporte);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonVentas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtoCliente = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtoPreveedores = new javax.swing.JButton();
        jButtonMarca = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 0));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(51, 255, 51));

        jButtonVentas.setBackground(new java.awt.Color(204, 204, 255));
        jButtonVentas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ventas.png"))); // NOI18N
        jButtonVentas.setText("Ventas");
        jButtonVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVentasActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bienes.png"))); // NOI18N
        jButton1.setText("Compras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtoCliente.setBackground(new java.awt.Color(204, 204, 255));
        jButtoCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/avatar.png"))); // NOI18N
        jButtoCliente.setText("Clientes");
        jButtoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoClienteActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/moto.png"))); // NOI18N
        jButton2.setText("Productos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtoPreveedores.setBackground(new java.awt.Color(204, 204, 255));
        jButtoPreveedores.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtoPreveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/repartidor.png"))); // NOI18N
        jButtoPreveedores.setText("Proveedores");
        jButtoPreveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoPreveedoresActionPerformed(evt);
            }
        });

        jButtonMarca.setBackground(new java.awt.Color(204, 204, 255));
        jButtonMarca.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imagen-de-marca.png"))); // NOI18N
        jButtonMarca.setText("Marca");
        jButtonMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarcaActionPerformed(evt);
            }
        });

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setForeground(new java.awt.Color(153, 255, 0));
        jDesktopPane1.setToolTipText("");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/report.png"))); // NOI18N
        jButton4.setText("Reportes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButtonVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButtoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButtoPreveedores)
                .addGap(18, 18, 18)
                .addComponent(jButtonMarca)
                .addContainerGap(64, Short.MAX_VALUE))
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jButtoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                    .addComponent(jButtonMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtoPreveedores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("jButton3");

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout.png"))); // NOI18N
        jMenu5.setText("Salir");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        menuBar.add(jMenu5);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVentasActionPerformed
        Detalle_V.setVisible(true);
    }//GEN-LAST:event_jButtonVentasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Compras.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoClienteActionPerformed
        Clientes.setVisible(true);
        try {
            Clientes.obtenerDatos();
        } catch (SQLException ex) {
            Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtoClienteActionPerformed

    private void jButtoPreveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoPreveedoresActionPerformed
           Proveedor.setVisible(true);
     try {
         Proveedor.obtenerDatos();
        } catch (SQLException ex) {
            Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtoPreveedoresActionPerformed

    private void jButtonMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMarcaActionPerformed
    Marca.setVisible(true);
    try {
            Marca.obtenerDatos();
        } catch (SQLException ex) {
            Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonMarcaActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      Productos.setVisible(true);
    try {
            Productos.obtenerDatos();
        } catch (SQLException ex) {
            Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     Reporte.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MDIMoto_Repuestos().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MDIMoto_Repuestos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtoCliente;
    private javax.swing.JButton jButtoPreveedores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonMarca;
    private javax.swing.JButton jButtonVentas;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem5;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
