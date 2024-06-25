/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.DAOMarca;
import Modelo.Marca;
import javax.swing.JOptionPane;
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

/**
 *
 * @author admin
 */
public class JInternalFrameMarca extends javax.swing.JInternalFrame {

   
    public JInternalFrameMarca() {
        initComponents();
        jTextID_Marca.setEnabled(false);
    }
      public void obtenerDatos() throws SQLException {
        List<Marca> marcas = new DAOMarca().ObtenerDatos();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"ID_Marca", "Marca"};
        modelo.setColumnIdentifiers(columnas);
        for(Marca au : marcas){
             String[] renglon = {Integer.toString(au.getID_Marca()), au.getMarca()};
             modelo.addRow(renglon);
          }
        jTableMarca.setModel(modelo);   
    } 
      
       public void  actualizarAutor() throws SQLException {
     int  id = Integer.parseInt(this.jTextID_Marca.getText());
     String nom = this.jTextMarca.getText();
  
     Marca marc = new Marca(id, nom);
     
     DAOMarca dao = new DAOMarca();
     int res = dao.Actualizar(marc);
     if (res == 0){
         JOptionPane.showMessageDialog(rootPane,
                 "Marca Actualizada" );
     }else{
         JOptionPane.showMessageDialog(rootPane, 
                 "¡Ocurrion un Error!");
         
     }
       }
       public void  limpiarCampos(){
        jTextID_Marca.setText("");
        jTextMarca.setText("");
    }
         public void actualizarInterfaMarca (List<Marca> mar) {
        DefaultTableModel modelo = new DefaultTableModel();
      
      String[] columnas = {"ID_Maraca","Marca"};
        modelo.setColumnIdentifiers(columnas);
        for (Marca mac:mar ){
          String[]  renglon = {Integer.toString(mac.getID_Marca()),
        mac.getMarca()};
          modelo.addRow(renglon);
        }
       jTableMarca.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextID_Marca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonEditar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextBuscar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMarca = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(395, 442));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ID de la Marca:");

        jTextID_Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextID_MarcaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Marca");

        jTextMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextMarcaKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButtonEditar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonActualizar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar (2).png"))); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonBuscar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/moto.png"))); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonGuardar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disquete (1).png"))); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Buscar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextID_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(6, 6, 6))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(153, 153, 153)
                    .addComponent(jLabel5)
                    .addContainerGap(228, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextID_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jTextBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jLabel5)
                    .addContainerGap(181, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jTableMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID Marca", "Marca"
            }
        ));
        jScrollPane1.setViewportView(jTableMarca);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(6, 6, 6))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(153, 153, 153)
                    .addComponent(jLabel13)
                    .addContainerGap(402, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(204, 204, 204))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jLabel13)
                    .addContainerGap(181, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        try {
           
           String ma =jTextMarca.getText();
           
           
           
           if (ma.contentEquals("") ){
               JOptionPane.showMessageDialog(rootPane, 
                       "Todos los campos son obligatorios");
               
           }else {
               try {
                  
                   Marca auto = new Marca(ma);
                   DAOMarca dao = new DAOMarca();
                   if (dao.Insertar(auto) == 0) {
                       JOptionPane.showMessageDialog(rootPane,
                               "Registro agregado");
                   }
                   
               } catch (HeadlessException | SQLException e) {
                   JOptionPane.showMessageDialog(rootPane, 
                           "No se agrego el registro");
               }
           }
           obtenerDatos();
           
           limpiarCampos();
       } catch (SQLException ex) {
          Logger.getLogger(JInternalFrameCliente.class.getName()).
                   log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButtonGuardarActionPerformed
private boolean busquedaActivaMarca = false;
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
      String terminoBusqueda = jTextBuscar.getText().trim();
    if (!terminoBusqueda.isEmpty()) {
         try {
             actualizarInterfaMarca(new DAOMarca().buscarMarca(terminoBusqueda));
         } catch (SQLException ex) {
             Logger.getLogger(JInternalFrameMarca.class.getName()).log(Level.SEVERE, null, ex);
         }
        busquedaActivaMarca = true;
    } else if (busquedaActivaMarca) {
         try {
             actualizarInterfaMarca(new DAOMarca().ObtenerDatos());
         } catch (SQLException ex) {
             Logger.getLogger(JInternalFrameMarca.class.getName()).log(Level.SEVERE, null, ex);
         }
        busquedaActivaMarca = false;
    } else {
        JOptionPane.showMessageDialog(null,
                "Por favor, ingrese un término de búsqueda.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    } 
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jTextID_MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextID_MarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextID_MarcaActionPerformed

    private void jTextMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextMarcaKeyTyped
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car <'A' || car > 'Z')
            && car != 'á' //Minuscula
            && car != 'é'
            && car != 'í'
            && car != 'ó'
            && car != 'ú'
            && car != 'Á'
            && car != 'É'
            && car != 'Í'
            && car != 'Ó'
            && car != 'Ú'
            && car != 'Ü'
            && car != 'ü'
            && car != 'Ñ'
            && car != 'ñ'
            && (car != (char) KeyEvent. VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextMarcaKeyTyped

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        int fila = this.jTableMarca.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(rootPane, 
                    "Seleccione un registro de la tabla");
            
        }else{
           try{
               int id = Integer.parseInt((String) this.jTableMarca.
                       getValueAt(fila, 0).toString());
               String Marc = (String) this.jTableMarca.getValueAt(fila, 1);
               
               
               jTextID_Marca.setText("" + id);
               jTextMarca.setText(Marc);
               
               
           } catch (NumberFormatException e){
               JOptionPane.showMessageDialog(rootPane, 
                       "¡Ocurrio un error!"+e.getMessage());
           }
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
         try{
            actualizarAutor();
        } catch (SQLException ex) {
            Logger.getLogger(JInternalFrameMarca.class.getName()).
                    log(Level.SEVERE,null,ex);
            
        }
        try{
            obtenerDatos();
        }catch (SQLException ex) {
            Logger.getLogger(JInternalFrameMarca.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        limpiarCampos();
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int fila = this.jTableMarca.getSelectedRow();
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
                        int id = Integer.parseInt((String) this.jTableMarca.
                                getValueAt(fila, 0).toString());
                        DAOMarca dao = new DAOMarca();
                        dao.Eliminar(id);
                        obtenerDatos();
                    }catch (SQLException ex){
                        Logger.getLogger(JInternalFrameMarca.class.getName()).
                                log(Level.SEVERE,null,ex);
                        
                    }
                }
            }
            if (resp == JOptionPane.CLOSED_OPTION){
                JOptionPane.showMessageDialog(rootPane, 
                        "Ninguna accion realizada");
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMarca;
    private javax.swing.JTextField jTextBuscar;
    private javax.swing.JTextField jTextID_Marca;
    private javax.swing.JTextField jTextMarca;
    // End of variables declaration//GEN-END:variables
}
