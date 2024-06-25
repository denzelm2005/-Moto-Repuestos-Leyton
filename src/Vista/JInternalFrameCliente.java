/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Cliente;
import Modelo.DAOCliente;
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

public class JInternalFrameCliente extends javax.swing.JInternalFrame {

    public JInternalFrameCliente() {
        initComponents();
        jTextID_Cliente.setEnabled(false);
    }

    public void obtenerDatos() throws SQLException {
        List<Cliente> clientes = new DAOCliente().ObtenerDatos();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"ID_Cliente", "Cédula", "Nombre", "Apellidos", "Télefono"};
        modelo.setColumnIdentifiers(columnas);
        for(Cliente au : clientes){
             String[] renglon = {Integer.toString(au.getID_Cliente()), au.getCédula(),
             au.getNombre(), au.getApelllidos(), au.getTeléfono()};
             modelo.addRow(renglon);
          }
        jTableClientes.setModel(modelo); 
        
      
        jTableClientes.setAutoResizeMode(jTableClientes.AUTO_RESIZE_OFF);
       var columnModel = jTableClientes.getColumnModel();
     columnModel.getColumn(0).setPreferredWidth(70); 
     columnModel.getColumn(1).setPreferredWidth(140); 
     columnModel.getColumn(2).setPreferredWidth(107); 
     columnModel.getColumn(3).setPreferredWidth(107); 
     columnModel.getColumn(4).setPreferredWidth(107); 
    } 
    
    public void actualizarInterfaCliente (List<Cliente> Clien) {
        DefaultTableModel modelo = new DefaultTableModel();
      
      String[] columnas = {"ID_Cliente","Cédula","Nombre","Apelllidos","Teléfono"};
        modelo.setColumnIdentifiers(columnas);
        for (Cliente Client :Clien ){
          String[]  renglon = {Integer.toString(Client.getID_Cliente()),
         Client.getCédula(),Client.getNombre(),
         Client.getApelllidos(),Client.getTeléfono()};
          modelo.addRow(renglon);
        }
       jTableClientes.setModel(modelo);
       
       
        jTableClientes.setAutoResizeMode(jTableClientes.AUTO_RESIZE_OFF);
       var columnModel = jTableClientes.getColumnModel();
     columnModel.getColumn(0).setPreferredWidth(70); 
     columnModel.getColumn(1).setPreferredWidth(140); 
     columnModel.getColumn(2).setPreferredWidth(107); 
     columnModel.getColumn(3).setPreferredWidth(107); 
     columnModel.getColumn(4).setPreferredWidth(107); 
    }
    
    
    public void  actualizarCliente() throws SQLException {
     int  id = Integer.parseInt(this.jTextID_Cliente.getText());
     String Ced = this.jTextCedula.getText();
     String Nom = this.jTextNombre.getText();
     String Apell = this.jTextApellidos.getText();
        String Tele = this.jTextTelefono.getText();
     
     
     Cliente Client = new Cliente(id, Ced, Nom, Apell, Tele);
     
     DAOCliente dao = new DAOCliente();
     int res = dao.Actualizar(Client);
     if (res == 0){
         JOptionPane.showMessageDialog(rootPane,
                 "Cliente Actualizado" );
     }else{
         JOptionPane.showMessageDialog(rootPane, 
                 "¡Ocurrion un Error!");
         
     }
    }
    
    
     public void  limpiarCampos(){
        jTextID_Cliente.setText("");
        jTextNombre.setText("");
        jTextApellidos.setText("");
        jTextCedula.setText("");
        jTextTelefono.setText("");
    }
     private void buscaDatosCliente(String dato) throws SQLException {
        List<Cliente> clientes = new DAOCliente().busquedaCliente(dato);
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String[] columnas = {"id_cliente","Cedúla","Nombres","Apellidos",
            "Teléfono"};
        modelo.setColumnIdentifiers(columnas);
        for (Cliente cli : clientes) {
            
            String[] renglon = {Integer.toString(cli.getID_Cliente()),
            cli.getCédula(), cli.getNombre(), cli.getApelllidos(), cli.getTeléfono()};
            modelo.addRow(renglon);
        }
        jTableClientes.setModel(modelo);
        
    
        jTableClientes.setAutoResizeMode(jTableClientes.AUTO_RESIZE_OFF);
       var columnModel = jTableClientes.getColumnModel();
     columnModel.getColumn(0).setPreferredWidth(70); 
     columnModel.getColumn(1).setPreferredWidth(140); 
     columnModel.getColumn(2).setPreferredWidth(107); 
     columnModel.getColumn(3).setPreferredWidth(107); 
     columnModel.getColumn(4).setPreferredWidth(107); 
  
  
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
        jTextID_Cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextApellidos = new javax.swing.JTextField();
        jButtonEditar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextBuscar = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jTextCedula = new javax.swing.JFormattedTextField();
        jTextTelefono = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(395, 442));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ID del Cliente:");

        jTextID_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextID_ClienteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombres:");

        jTextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombreKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Cédula:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Apellidos: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Teléfono:");

        jTextApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextApellidosActionPerformed(evt);
            }
        });
        jTextApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextApellidosKeyTyped(evt);
            }
        });

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Buscar");

        jButtonBuscar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
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

        try {
            jTextCedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextApellidos)
                    .addComponent(jTextNombre)
                    .addComponent(jTextID_Cliente)
                    .addComponent(jTextCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jTextTelefono, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextID_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(171, 171, 171))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jTextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jLabel5)
                    .addContainerGap(317, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Cliente", "Nombres", "Apellidos", "Cédula", "Teléfono"
            }
        ));
        jScrollPane1.setViewportView(jTableClientes);

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
                    .addContainerGap(399, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(204, 204, 204))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jLabel13)
                    .addContainerGap(313, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextID_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextID_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextID_ClienteActionPerformed

    private void jTextApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextApellidosActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
     try {
           
           String Ced =jTextCedula.getText();
           String Nom = jTextNombre.getText();
           String Apell = jTextApellidos.getText();
           String Tel = jTextTelefono.getText();
           
           
           if (Ced.contentEquals("") || Nom.contentEquals("")
                   || Apell.contentEquals("") || Tel.contentEquals("")){
               JOptionPane.showMessageDialog(rootPane, 
                       "Todos los campos son obligatorios");
               
           }else {
               try {
                  
                   Cliente auto = new Cliente(Ced, Nom, Apell, Tel);
                   DAOCliente dao = new DAOCliente();
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
private boolean busquedaActivaCliente = false;
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
String terminoBusqueda = jTextBuscar.getText().trim();
    if (!terminoBusqueda.isEmpty()) {
         try {
             actualizarInterfaCliente(new DAOCliente().busquedaCliente(terminoBusqueda));
         } catch (SQLException ex) {
             Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, null, ex);
         }
        busquedaActivaCliente = true;
    } else if (busquedaActivaCliente) {
         try {
             actualizarInterfaCliente(new DAOCliente().ObtenerDatos());
         } catch (SQLException ex) {
             Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, null, ex);
         }
        busquedaActivaCliente = false;
    } else {
        JOptionPane.showMessageDialog(null,
                "Por favor, ingrese un término de búsqueda.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    }  
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jTextNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNombreKeyTyped
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
    }//GEN-LAST:event_jTextNombreKeyTyped

    private void jTextApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextApellidosKeyTyped
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
    }//GEN-LAST:event_jTextApellidosKeyTyped

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
         int fila = this.jTableClientes.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(rootPane, 
                    "Seleccione un registro de la tabla");
            
        }else{
           try{
               int id = Integer.parseInt((String) this.jTableClientes.
                       getValueAt(fila, 0).toString());
               String ced = (String) this.jTableClientes.getValueAt(fila, 1);
               String nom = (String) this.jTableClientes.getValueAt(fila, 2);
               String ape = (String) this.jTableClientes.getValueAt(fila, 3);
               String tele = (String) this.jTableClientes.getValueAt(fila, 4);
               
               jTextID_Cliente.setText("" + id);
               jTextCedula.setText(ced);
               jTextNombre.setText(nom);
               jTextApellidos.setText(ape);
             jTextTelefono.setText(tele);
               
           } catch (NumberFormatException e){
               JOptionPane.showMessageDialog(rootPane, 
                       "¡Ocurrio un error!"+e.getMessage());
           }
        }  
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
 try{
            actualizarCliente();
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
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int fila = this.jTableClientes.getSelectedRow();
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
                        int id = Integer.parseInt((String) this.jTableClientes.
                                getValueAt(fila, 0).toString());
                        DAOCliente dao = new DAOCliente();
                        dao.Eliminar(id);
                        obtenerDatos();
                    }catch (SQLException ex){
                        Logger.getLogger(JInternalFrameCliente.class.getName()).
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextApellidos;
    private javax.swing.JTextField jTextBuscar;
    private javax.swing.JFormattedTextField jTextCedula;
    private javax.swing.JTextField jTextID_Cliente;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JFormattedTextField jTextTelefono;
    // End of variables declaration//GEN-END:variables
}
