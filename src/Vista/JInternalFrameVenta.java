/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Venta;
import Modelo.DAOVenta;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Date; 
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import net.sf.jasperreports.engine.JRException;



/**
 *
 * @author admin
 */
public class JInternalFrameVenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrameVenta
     */
    public JInternalFrameVenta() {
        initComponents();
        jTextFecha_Venta.setEnabled(false);
        jTextID_Venta.setEnabled(false);
    }
    
   public void obtenerDatos() throws SQLException {
    List<Venta> ventas = new DAOVenta().obtenerDatos();
    DefaultTableModel modelo = new DefaultTableModel();
    String[] columnas = {"ID_Venta", "Fecha_venta", "ID_Cliente"};
    modelo.setColumnIdentifiers(columnas);
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");

    for (Venta venta : ventas) {
        String fechaFormateada = venta.getFecha_venta().format(formatter);
        String[] renglon = {Integer.toString(venta.getID_Venta()), fechaFormateada, Integer.toString(venta.getID_Cliente())};
        modelo.addRow(renglon);
    }
    jTableVenta.setModel(modelo);
}
   
    
    
  public void actualizarVenta() throws SQLException {
   try {
    // Obtener los datos de la venta a partir de la interfaz de usuario
    int idVenta = Integer.parseInt(jTextID_Venta.getText());
    String fechaString = jTextFecha_Venta.getText();
    int idCliente = Integer.parseInt(jTextID_Cliente.getText());

    // Verificar que los campos no estén vacíos
    if (fechaString.isEmpty() || jTextID_Cliente.getText().isEmpty()) {
        JOptionPane.showMessageDialog(rootPane, "Todos los campos son obligatorios");
    } else {
        try {
            // Convertir el String a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            LocalDateTime fechaVenta = LocalDateTime.parse(fechaString, formatter);

            // Crear el objeto Venta con los nuevos datos
            Venta venta = new Venta(idCliente, fechaVenta);
            DAOVenta dao = new DAOVenta();

            // Actualizar la venta en la base de datos
            int filasAfectadas = dao.Actualizar(venta);

            // Verificar si se actualizó correctamente
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(rootPane, "Error al actualizar la venta");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Venta actualizada correctamente");
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(rootPane, "Formato de fecha incorrecto");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error de base de datos: " + e.getMessage());
        }
    }
} catch (NumberFormatException ex) {
    Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, "Error de formato numérico", ex);
} catch (Exception ex) {
    Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, "Error inesperado", ex);
}
  }
  
    public void  limpiarCampos(){
        jTextID_Venta.setText("");
        jTextFecha_Venta.setText("");
        jTextID_Cliente.setText("");
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dAODetalle_Venta1 = new Modelo.DAODetalle_Venta();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextID_Venta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextID_Cliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFecha_Venta = new javax.swing.JTextField();
        jButtoneditar = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButtoneliminar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jBVerReporte = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTableVenta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ID de Venta:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("ID de Cliente:");

        jTextID_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextID_ClienteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Fecha de Venta:");

        jButtoneditar.setBackground(new java.awt.Color(204, 204, 255));
        jButtoneditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtoneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        jButtoneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoneditarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Buscar");

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtoneliminar.setBackground(new java.awt.Color(204, 204, 255));
        jButtoneliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtoneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar (2).png"))); // NOI18N
        jButtoneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoneliminarActionPerformed(evt);
            }
        });

        jButtonGuardar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disquete (1).png"))); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jBVerReporte.setBackground(new java.awt.Color(204, 204, 255));
        jBVerReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bill.png"))); // NOI18N
        jBVerReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextID_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextID_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(285, 285, 285)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButtoneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtoneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jBVerReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFecha_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextID_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextID_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFecha_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtoneditar)
                    .addComponent(jButton3)
                    .addComponent(jButtoneliminar)
                    .addComponent(jButtonGuardar)
                    .addComponent(jBVerReporte))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextID_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextID_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextID_ClienteActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
 try {
    int idC = Integer.parseInt(this.jTextID_Cliente.getText());

    if (jTextID_Cliente.getText().isEmpty()) {
        JOptionPane.showMessageDialog(rootPane, "Todos los campos son obligatorios");
    } else {
        try {
            // Capturar la fecha y hora actual
            LocalDateTime fechaVenta = LocalDateTime.now();

            // Crear el objeto Venta con LocalDateTime
            Venta venta = new Venta(idC, fechaVenta);
            DAOVenta dao = new DAOVenta();
            if (dao.Insertar(venta) > 0) {
                JOptionPane.showMessageDialog(rootPane, "No se agrego el registro agregado");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Se agregó el registro ");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(rootPane, "No se agregó el registro: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error de base de datos: " + e.getMessage());
        }
    }
    obtenerDatos();
    limpiarCampos();
} catch (NumberFormatException ex) {
    Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, "Error de formato numérico", ex);
} catch (Exception ex) {
    Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, "Error inesperado", ex);
}
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtoneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoneliminarActionPerformed
        int fila = this.jTableVenta.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(rootPane,
                    "Seleccione un registro de la tabla");
        }else{
            JDialog.setDefaultLookAndFeelDecorated(true);
            int resp = JOptionPane.showConfirmDialog(rootPane, 
                    "¿Estas seguro de eliminar?", "Aceptar", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (resp == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(rootPane, "Registro no borrado");
                
            }else{
                if (resp == JOptionPane.YES_OPTION){
                    try{
                        int id = Integer.parseInt((String) this.jTableVenta.
                                getValueAt(fila, 0).toString());
                        DAOVenta dao = new DAOVenta();
                        dao.Eliminar(id);
                        obtenerDatos();
                    }catch (SQLException ex){
                        Logger.getLogger(JInternalFrameVenta.class.getName()).
                                log(Level.SEVERE,null,ex);
                        
                    }
                }
            }
            if (resp == JOptionPane.CLOSED_OPTION){
                JOptionPane.showMessageDialog(rootPane, 
                        "Ninguna accion realizada");
            }
        }
    }//GEN-LAST:event_jButtoneliminarActionPerformed

    private void jButtoneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoneditarActionPerformed
     int fila = this.jTableVenta.getSelectedRow();
     if (fila == -1) {
    JOptionPane.showMessageDialog(rootPane, "Seleccione un registro de la tabla");
     } else {
      try {
        int id = Integer.parseInt(this.jTableVenta.getValueAt(fila, 0).toString());
        
        // Recuperar el LocalDateTime desde la tabla y formatearlo correctamente
      // Recuperar el LocalDateTime desde la tabla y formatearlo correctamente
String fech = this.jTableVenta.getValueAt(fila, 1).toString();

// Corrección del formato del DateTimeFormatter
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
LocalDateTime fechaVenta = LocalDateTime.parse(fech, formatter);
String fechaFormateada = fechaVenta.format(formatter);
        
        int idc = Integer.parseInt(this.jTableVenta.getValueAt(fila, 2).toString());
        
        jTextID_Venta.setText(String.valueOf(id));
        jTextFecha_Venta.setText(fechaFormateada);
        jTextID_Cliente.setText(String.valueOf(idc));
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(rootPane, "¡Ocurrió un error! " + e.getMessage());
        e.printStackTrace();
    }
}
    }//GEN-LAST:event_jButtoneditarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   try{
            actualizarVenta();
        } catch (SQLException ex) {
            Logger.getLogger(JInternalFrameCliente.class.getName()).
                    log(Level.SEVERE,null,ex);
            
        }
        try{
            obtenerDatos();
        }catch (SQLException ex) {
            Logger.getLogger(JInternalFrameCliente
                    .class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        limpiarCampos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jBVerReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerReporteActionPerformed
         DAOVenta daoventa = new  DAOVenta();
     int numfac = 0;
        
        if(numfac!=0){
            try {
                
                
                daoventa.reporteFactura(numfac);
                
            }catch (JRException ex) {
                Logger.getLogger(JInternalFrameDetalles_Venta.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, 
                    "No tiene productos, no se puede mostrar la factura");
        }
    }//GEN-LAST:event_jBVerReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Modelo.DAODetalle_Venta dAODetalle_Venta1;
    private javax.swing.JButton jBVerReporte;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtoneditar;
    private javax.swing.JButton jButtoneliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVenta;
    private javax.swing.JTextField jTextFecha_Venta;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextID_Cliente;
    private javax.swing.JTextField jTextID_Venta;
    // End of variables declaration//GEN-END:variables
}
