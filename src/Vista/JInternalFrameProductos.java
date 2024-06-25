/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;



import Modelo.Producto;
import Modelo.DAOProducto;
import Modelo.DAOMarca;
import Modelo.Marca;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import net.sf.jasperreports.engine.JRException;
/**
 *
 * @author admin
 */
public class JInternalFrameProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrameProducros
     */
    int idMarca;
    public JInternalFrameProductos() throws SQLException {
        initComponents();
        jTextID_Producto.setEnabled(false);
        llenarcombo();
    }

    
    
    public void obtenerDatos() throws SQLException {
        List<Producto> productos = new DAOProducto().ObtenerDatos();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"ID_Producto", "Nombre", "Modelo", "Stock",
        "Precio_Venta","Precio_Compra","ID_Marca"};
        modelo.setColumnIdentifiers(columnas);
        for(Producto au : productos){
             String[] renglon = {Integer.toString(au.getID_Producto()), au.getNombre(),
             au.getModelo(), Integer.toString(au.getStock()),Float.toString(au.getPrecio_Venta()
             ), Float.toString(au.getPrecio_Compra()),Integer.toString(au.getID_Marca())};
             modelo.addRow(renglon);
          }
        jTableProductos.setModel(modelo); 
        
        jTableProductos.setAutoResizeMode(jTableProductos.AUTO_RESIZE_OFF);
       var columnModel = jTableProductos.getColumnModel();
     columnModel.getColumn(1).setPreferredWidth(102); 
     columnModel.getColumn(2).setPreferredWidth(94); 
     columnModel.getColumn(3).setPreferredWidth(94);
     columnModel.getColumn(4).setPreferredWidth(93);
     columnModel.getColumn(5).setPreferredWidth(96);
    }
    
    public void llenarcombo() throws SQLException{
        List<Marca> marcas = new DAOMarca().ObtenerDatos();
         for (int i = 0; i < marcas.size(); i++) {
             
            jComboBoxMarca.addItem(new Marca(marcas.get(i).getID_Marca(), 
            marcas.get(i).getMarca()));
        }
       idMarca=jComboBoxMarca.getItemAt(jComboBoxMarca.getSelectedIndex()).getID_Marca();
    }
    
    
     public void  limpiarCampos(){
        jTextBuscar.setText("");
        jTextNombre.setText("");
        jTextModelo.setText("");
        jTextPrecio_Venta.setText("");
        jTextPrecio_Compra.setText("");
        jTextStock.setText("");
    }
     
      public void  actualizarProducto() throws SQLException {
     int  id = Integer.parseInt(this.jTextID_Producto.getText());
     String nom = this.jTextNombre.getText();
     String mod= this.jTextModelo.getText();
     Float precio_v = Float.valueOf(this.jTextPrecio_Venta.getText());
     Float precio_c = Float.valueOf(this.jTextPrecio_Compra.getText());
     int  stock = Integer.parseInt(this.jTextStock.getText());
     int  id_marca = idMarca;
     
     
     Producto Producto = new Producto(id, nom, mod,
             stock, precio_v, precio_c, id_marca);
     
     DAOProducto dao = new DAOProducto();
     int res = dao.Actualizar(Producto);
     if (res == 0){
         JOptionPane.showMessageDialog(rootPane,
                 "Producto Actualizado" );
     }else{
         JOptionPane.showMessageDialog(rootPane, 
                 "¡Ocurrion un Error!");
         
     }
    }
      
     public void actualizarInterfaProducto(List<Producto> produ) {
        DefaultTableModel modelo = new DefaultTableModel();
      
      String[] columnas = {"ID_Producto", "Nombre", "Modelo", "Stock",
        "Precio_Venta","Precio_Compra","ID_Marca"};
        modelo.setColumnIdentifiers(columnas);
        for (Producto productos :produ ){
          String[]  renglon = {Integer.toString(productos.getID_Producto()),
         productos.getNombre(),productos.getModelo(),Integer.toString(productos.getStock()),
          Float.toString(productos.getPrecio_Venta()), Float.toString(productos.getPrecio_Compra()),
          Integer.toString(productos.getID_Marca())};
          modelo.addRow(renglon);
        }
       jTableProductos.setModel(modelo);
       
         jTableProductos.setAutoResizeMode(jTableProductos.AUTO_RESIZE_OFF);
       var columnModel = jTableProductos.getColumnModel();
     columnModel.getColumn(1).setPreferredWidth(110); 
     columnModel.getColumn(2).setPreferredWidth(100); 
     columnModel.getColumn(3).setPreferredWidth(100);
     columnModel.getColumn(4).setPreferredWidth(100);
     columnModel.getColumn(5).setPreferredWidth(100);
    } 
      
      private void buscaDatosProducto(String dato) throws SQLException {
        List<Producto> Product = new DAOProducto().busquedaProducto(dato);
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String[] columnas = {"ID_Producto","Nombre","Modelo","Stock",
            "Precio_Venta", "Precio_Compra", "ID_Marca"};
        modelo.setColumnIdentifiers(columnas);
        for (Producto Produc :Product) {
            
            String[] renglon = {Integer.toString(Produc.getID_Producto()),
            Produc.getNombre(),Produc.getModelo(), Integer.toString(Produc.getStock()), 
            Float.toString(Produc.getPrecio_Venta()),
            Float.toString(Produc.getPrecio_Compra()),
            Integer.toString(Produc.getID_Marca())};
            modelo.addRow(renglon);
            
        }
        jTableProductos.setModel(modelo);
        
    
         jTableProductos.setAutoResizeMode(jTableProductos.AUTO_RESIZE_OFF);
       var columnModel = jTableProductos.getColumnModel();
     columnModel.getColumn(1).setPreferredWidth(110); 
     columnModel.getColumn(2).setPreferredWidth(100); 
     columnModel.getColumn(3).setPreferredWidth(100);
     columnModel.getColumn(4).setPreferredWidth(100);
     columnModel.getColumn(5).setPreferredWidth(100);
  
  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextID_Producto = new javax.swing.JTextField();
        jTextNombre = new javax.swing.JTextField();
        jTextModelo = new javax.swing.JTextField();
        jTextStock = new javax.swing.JTextField();
        jTextPrecio_Venta = new javax.swing.JTextField();
        jTextBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonEditar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextPrecio_Compra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jButtonProductos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ID Producto:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombre :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Modelo: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Stock:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Precio Venta:");

        jTextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombreKeyTyped(evt);
            }
        });

        jTextModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextModeloActionPerformed(evt);
            }
        });
        jTextModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextModeloKeyTyped(evt);
            }
        });

        jTextBuscar.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Buscar");

        jButtonEditar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar (1).png"))); // NOI18N
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonActualizar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar (2).png"))); // NOI18N
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonGuardar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disquete (1).png"))); // NOI18N
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonBuscar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/engranaje.png"))); // NOI18N
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Precio Compra:");

        jTextPrecio_Compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPrecio_CompraActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("ID Marca");

        jComboBoxMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMarcaItemStateChanged(evt);
            }
        });
        jComboBoxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMarcaActionPerformed(evt);
            }
        });

        jButtonProductos.setBackground(new java.awt.Color(204, 204, 255));
        jButtonProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/moto.png"))); // NOI18N
        jButtonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextID_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxMarca, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextBuscar, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextPrecio_Compra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addComponent(jTextPrecio_Venta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addComponent(jTextStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addComponent(jTextModelo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextID_Producto))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextPrecio_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jTextPrecio_Compra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Producto", "Nombre", "Modelo", "Precio_Venta", "Precio_Compra", "Stock", "ID Marca"
            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private boolean busquedaActivaProducto = false;
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
         String terminoBusqueda = jTextBuscar.getText().trim();
    if (!terminoBusqueda.isEmpty()) {
         try {
             actualizarInterfaProducto(new DAOProducto().busquedaProducto(terminoBusqueda));
         } catch (SQLException ex) {
             Logger.getLogger(JInternalFrameProveedor.class.getName()).log(Level.SEVERE, null, ex);
         }
        busquedaActivaProducto = true;
    } else if (busquedaActivaProducto) {
         try {
             actualizarInterfaProducto(new DAOProducto().ObtenerDatos());
         } catch (SQLException ex) {
             Logger.getLogger(JInternalFrameProveedor.class.getName()).log(Level.SEVERE, null, ex);
         }
        busquedaActivaProducto = false;
    } else {
        JOptionPane.showMessageDialog(null,
                "Por favor, ingrese un término de búsqueda.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
    }  
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        
        try {
           
          
           
           
    if ((jTextNombre.getText().contentEquals("")) ||
            (jTextModelo.getText().contentEquals("l")) || 
            (jTextPrecio_Compra.getText().contentEquals("")) ||
            (jTextPrecio_Venta.getText().contentEquals("")) ||
            (jTextStock.getText().contentEquals("")) 
            ){
              JOptionPane.showMessageDialog(rootPane, "Todos los Campos son obligatorios ");
               
           }else {
               try {
                    String Nom =jTextNombre.getText();
           String Mod = jTextModelo.getText();
           int Stock = Integer.parseInt(this.jTextStock.getText());
           Float Precio_V = Float.valueOf(this.jTextPrecio_Venta.getText());
           Float Precio_C = Float.valueOf(this.jTextPrecio_Compra.getText());
           int Marca = idMarca;
                  
                   Producto auto = new Producto(Nom, Mod, Stock, 
                           Precio_V, Precio_C, Marca);
                   DAOProducto dao = new DAOProducto();
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

    private void jTextPrecio_CompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPrecio_CompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPrecio_CompraActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
  int fila = this.jTableProductos.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(rootPane, "Seleccione un registro de la tabla");
    } else {
        try {
            int id_Producto = Integer.parseInt((String) this.jTableProductos.getValueAt(fila, 0).toString());
            String nom = (String) this.jTableProductos.getValueAt(fila, 1);
            String mod = (String) this.jTableProductos.getValueAt(fila, 2);
            int stock = Integer.parseInt((String) this.jTableProductos.getValueAt(fila, 3).toString());
            Float precio_v = Float.valueOf((String) this.jTableProductos.getValueAt(fila, 4).toString());
            Float precio_c = Float.valueOf((String) this.jTableProductos.getValueAt(fila, 5).toString());
            int id_marca = Integer.parseInt((String) this.jTableProductos.getValueAt(fila, 6).toString());

            jTextID_Producto.setText("" + id_Producto);
            jTextNombre.setText(nom);
            jTextModelo.setText(mod);
            jTextPrecio_Compra.setText(String.valueOf(precio_c));
            jTextPrecio_Venta.setText(String.valueOf(precio_v));
            jTextStock.setText("" + stock);

            // Establecer el valor seleccionado en el JComboBox
            for (int i = 0; i < jComboBoxMarca.getItemCount(); i++) {
                String item = jComboBoxMarca.getItemAt(i).toString();
                String itemId = item.split(" - ")[0]; // Asumimos que el formato es "id - nombre"
                if (itemId.equals(String.valueOf(id_marca))) {
                    jComboBoxMarca.setSelectedIndex(i);
                    break;
                }
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "¡Ocurrió un error!" + e.getMessage());
        }
    }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
     try{
        actualizarProducto();
        } catch (SQLException ex) {
            Logger.getLogger(JInternalFrameProductos.class.getName()).
                    log(Level.SEVERE,null,ex);
            
        }
        try{
            obtenerDatos();
        }catch (SQLException ex) {
            Logger.getLogger(JInternalFrameProductos
                    .class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        limpiarCampos();
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
       int fila = this.jTableProductos.getSelectedRow();
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
                        int id = Integer.parseInt((String) this.jTableProductos.
                                getValueAt(fila, 0).toString());
                        DAOProducto dao = new DAOProducto();
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

    private void jTextModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextModeloActionPerformed

    private void jComboBoxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMarcaActionPerformed

    private void jComboBoxMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMarcaItemStateChanged
    idMarca= jComboBoxMarca.getItemAt(jComboBoxMarca.getSelectedIndex()).getID_Marca();
  
    
    }//GEN-LAST:event_jComboBoxMarcaItemStateChanged

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

    private void jTextModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextModeloKeyTyped
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextModeloKeyTyped

    private void jButtonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductosActionPerformed
      DAOProducto daoproducto = new DAOProducto();
        try {
            daoproducto.productoExistenciaBaja();
            
        }catch (JRException ex){
            Logger.getLogger(JInternalFrameProductos.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonProductosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonProductos;
    private javax.swing.JComboBox<Marca> jComboBoxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JTextField jTextBuscar;
    private javax.swing.JTextField jTextID_Producto;
    private javax.swing.JTextField jTextModelo;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextPrecio_Compra;
    private javax.swing.JTextField jTextPrecio_Venta;
    private javax.swing.JTextField jTextStock;
    // End of variables declaration//GEN-END:variables
}
