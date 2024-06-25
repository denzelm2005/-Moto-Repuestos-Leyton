/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controlador.MDIMoto_Repuestos;
import Modelo.*;
import Modelo.DAOVenta;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author admin
 */
public class JInternalFrameDetalles_Venta extends javax.swing.JInternalFrame {
 private JInternalFrameCliente Cliente;
 private java.sql.Date fech; 
 
 public DefaultTableModel modeloTablaVenta;
    private Object[] objetoVentaTabla = new Object[8];
    private Double total = 0.0;
    private int item = 0;
    private int id_venta = 0;
    private int cantidad = 0;
    private int idproducto;
    /**
     * Creates new form NewJInternalFrameDetalles_Venta
     */
    public JInternalFrameDetalles_Venta() throws SQLException {
        initComponents();
       Cliente = new JInternalFrameCliente();
       jDialogCliente.setLocationRelativeTo(null);
       jDialogProducto.setLocationRelativeTo(null);
        fech = java.sql.Date.valueOf(LocalDate.now());
        jlabelFechaVent.setText(fech.toString());
        jTextNombreCliente.setEnabled(true);
        jTextID_Cliente.setEnabled(false);
        modeloTablaVenta = (DefaultTableModel) jTableVenta_Productos.getModel();
        DAOVenta daoventa = new DAOVenta();
        id_venta = daoventa.obtenerUltimoNumFactura();
        jTextID_Venta.setText(Integer.toString(id_venta));
    }
    
    
    
   public void guardarVenta() throws SQLException {
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
              DAOVenta daoventa = new DAOVenta();
                id_venta = daoventa.obtenerUltimoNumFactura();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Registro agregado en venta ");
                 
                
                DAOVenta daoventa = new DAOVenta();
                id_venta = daoventa.obtenerUltimoNumFactura();
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(rootPane, "No se agregó el registro: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Error de base de datos: " + e.getMessage());
        }
         
        
       guardarDetalleVenta();
       limpiarCamposProductos();
      
    }
    
} catch (NumberFormatException ex) {
    Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, "Error de formato numérico", ex);
} catch (Exception ex) {
    Logger.getLogger(JInternalFrameCliente.class.getName()).log(Level.SEVERE, "Error inesperado", ex);
}
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
}
    
    public void guardarDetalleVenta() throws  SQLException {
       
       
       float precio;
       
       if (id_venta == 0 || jTableVenta_Productos.getRowCount() == 0) {
           
           JOptionPane.showMessageDialog(rootPane, "No se ha obtenido de factura o " 
           + "no tiene productos añadidos para vender");
           
       } else {
           for (int i = 0; i < jTableVenta_Productos.getRowCount(); i++) {
               precio = Float.parseFloat(jTableVenta_Productos.getValueAt(i, 5).toString());
               cantidad =  Integer.parseInt(jTableVenta_Productos.getValueAt(i, 6).toString());
               idproducto = Integer.parseInt(jTableVenta_Productos.getValueAt(i, 1).toString());
             
            
                     Detalle_Venta venta = new Detalle_Venta(precio, cantidad,
                             idproducto, id_venta);
                     
                     DAODetalle_Venta Venta = new DAODetalle_Venta();
                     if (Venta.InsertarDetalle_Venta(venta) == 0){
                         JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error, no se inserto el Detalle venta");
                     }else {
                         JOptionPane.showMessageDialog(rootPane,
                        "Registro listo para agregar en factura " + id_venta);
                         JOptionPane.showMessageDialog(rootPane,"Registro agregado en Detalle Venta" );
                          
           }
          }
       }
       actualizarExistencia();
   }
    
     public void actualizarExistencia() {
       
       int existenciaActual = 0;
       int nuevaExistencia = 0;
       
       for (int i = 0; i < jTableVenta_Productos.getRowCount(); i++) {
           idproducto=Integer.parseInt(jTableVenta_Productos.
                   getValueAt(i, 1).toString());
           cantidad=Integer.parseInt(jTableVenta_Productos.
                   getValueAt(i, 6).toString());
           DAOProducto daopro=new DAOProducto();
           
           try {
               List<Producto> p=daopro.busquedaProducto(String.valueOf
               (idproducto).toString());
               existenciaActual=p.get(0).getStock();
               nuevaExistencia=existenciaActual - cantidad;
               
               Producto pro=new Producto(idproducto, nuevaExistencia);
               DAOVenta daoproducto=new DAOVenta();
               if(daoproducto.actualizarExistenciaProductos(pro)==0){
                   JOptionPane.showMessageDialog(rootPane, "Existencia actualizada");
               }
           }catch (SQLException ex) {
               Logger.getLogger(JInternalFrameDetalles_Venta.class.getName()).
                       log(Level.SEVERE, null, ex);
           }
           
       }
   }

    private void buscaDatosCliente(String dato) throws SQLException {
        List<Cliente> clientes = new DAOCliente().busquedaCliente(dato);
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String[] columnas = {"id_cliente", "Cedúla", "Nombres", "Apellidos",
            "Teléfono"};
        modelo.setColumnIdentifiers(columnas);
        for (Cliente cli : clientes) {
            
            String[] renglon = {Integer.toString(cli.getID_Cliente()),
            cli.getCédula(), cli.getNombre(), cli.getApelllidos(), cli.getTeléfono()};
            modelo.addRow(renglon);
        }
        jTableBuscaCliente.setModel(modelo);
        
        jTableBuscaCliente.setAutoResizeMode(jTableBuscaCliente.AUTO_RESIZE_OFF);
       var columnModel = jTableBuscaCliente.getColumnModel();
     columnModel.getColumn(1).setPreferredWidth(117); 
     columnModel.getColumn(2).setPreferredWidth(86); 
     columnModel.getColumn(3).setPreferredWidth(86);
     columnModel.getColumn(4).setPreferredWidth(86);
  
    }
    
     private void buscaDatosProducto(String dato) throws SQLException {
        
       List<Producto> producto = new DAOProducto().busquedaProducto(dato);
       
       DefaultTableModel modelo = new DefaultTableModel();
       
       String[] columnas = {"ID_Producto", "Nombre", "Modelo", 
       "Stock", "Precio_Venta", "ID_Marca"};
       
       modelo.setColumnIdentifiers(columnas);
       for (Producto pro : producto) {
           
           String[] renglon = {Integer.toString(pro.getID_Producto()),
              pro.getNombre(),pro.getModelo(),
              Integer.toString(pro.getStock()),Float.toString(pro.getPrecio_Venta()),
               Integer.toString(pro.getID_Marca())};
               modelo.addRow(renglon);
           }
       jTablebuscarproducto.setModel(modelo);
       }
           
    public void limpiarCamposProductos(){
        jTextID_Productos.setText("0");
        jTextNombrepro.setText("");
        jTextFieldPrecio_produ.setText("0.0");
        jTextFieldStock.setText("0");
        jTextFieldCantidadProducto.setText("0");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogCliente = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldBuscarCliente = new javax.swing.JTextField();
        jBDbuscarCliente = new javax.swing.JButton();
        jBDagregarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBuscaCliente = new javax.swing.JTable();
        jDialogProducto = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldBuscarProducto = new javax.swing.JTextField();
        jBDbuscarProducto = new javax.swing.JButton();
        jBDagregarProducto = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablebuscarproducto = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextNombreCliente = new javax.swing.JTextField();
        jTextID_Cliente = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jButAgregar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextID_Productos = new javax.swing.JTextField();
        jButtonBuscar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextNombrepro = new javax.swing.JTextField();
        jTextFieldPrecio_produ = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldStock = new javax.swing.JTextField();
        jTextFieldCantidadProducto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldMarcas = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jBVerventa = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVenta_Productos = new javax.swing.JTable();
        jButtonGenerarVenta = new javax.swing.JButton();
        jButtonGenerarVenta1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jButtonquitar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextID_Venta = new javax.swing.JTextField();
        jTextFecha_Venta = new javax.swing.JTextField();
        jlabelFechaVent = new javax.swing.JLabel();

        jDialogCliente.setSize(new java.awt.Dimension(500, 300));

        jBDbuscarCliente.setText("Buscar");
        jBDbuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDbuscarClienteActionPerformed(evt);
            }
        });

        jBDagregarCliente.setText("Agregar");
        jBDagregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDagregarClienteActionPerformed(evt);
            }
        });

        jTableBuscaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id_cliente", "Cedúla ", "Nombres", "Apellidos", "Teléfono"
            }
        ));
        jScrollPane1.setViewportView(jTableBuscaCliente);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jTextFieldBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBDbuscarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jBDagregarCliente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBDagregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBDbuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogClienteLayout = new javax.swing.GroupLayout(jDialogCliente.getContentPane());
        jDialogCliente.getContentPane().setLayout(jDialogClienteLayout);
        jDialogClienteLayout.setHorizontalGroup(
            jDialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogClienteLayout.setVerticalGroup(
            jDialogClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialogProducto.setSize(new java.awt.Dimension(490, 380));

        jBDbuscarProducto.setText("Buscar");
        jBDbuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDbuscarProductoActionPerformed(evt);
            }
        });

        jBDagregarProducto.setText("Agregar");
        jBDagregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDagregarProductoActionPerformed(evt);
            }
        });

        jTablebuscarproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Nombre", "Modelo", "Stock", "Precio_Venta", "ID_Marca"
            }
        ));
        jScrollPane3.setViewportView(jTablebuscarproducto);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextFieldBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBDbuscarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBDagregarProducto))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDbuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDagregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogProductoLayout = new javax.swing.GroupLayout(jDialogProducto.getContentPane());
        jDialogProducto.getContentPane().setLayout(jDialogProductoLayout);
        jDialogProductoLayout.setHorizontalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogProductoLayout.setVerticalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Clientes", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("ID Cliente");

        jTextNombreCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreClienteActionPerformed(evt);
            }
        });

        jTextID_Cliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButtonBuscar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jButtonBuscar.setText("Buscar Cliente");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButAgregar.setBackground(new java.awt.Color(204, 204, 255));
        jButAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mas.png"))); // NOI18N
        jButAgregar.setText("Agregar");
        jButAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextID_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jButAgregar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextID_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jTextNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("ID Producto");

        jTextID_Productos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButtonBuscar1.setBackground(new java.awt.Color(204, 204, 255));
        jButtonBuscar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jButtonBuscar1.setText("Buscar Producto");
        jButtonBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscar1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nombre");

        jTextNombrepro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextNombrepro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreproActionPerformed(evt);
            }
        });

        jTextFieldPrecio_produ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Precio");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Cantidad");

        jTextFieldStock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jTextFieldCantidadProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Stock");

        jButtonAgregar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anadir-al-carrito.png"))); // NOI18N
        jButtonAgregar.setText("Añadir Producto");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Marca");

        jTextFieldMarcas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextFieldMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcasActionPerformed(evt);
            }
        });

        jTextFieldModelo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextFieldModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldModeloActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Modelo");

        jBVerventa.setBackground(new java.awt.Color(204, 204, 255));
        jBVerventa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBVerventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bill.png"))); // NOI18N
        jBVerventa.setText("Ver Venta");
        jBVerventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVerventaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPrecio_produ, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextID_Productos, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextNombrepro, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBVerventa)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextID_Productos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextNombrepro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldPrecio_produ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBVerventa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jTableVenta_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Código", "Nombre", "Modelo", "Marca", "Precio", "cantidad", "SubTotal"
            }
        ));
        jScrollPane2.setViewportView(jTableVenta_Productos);

        jButtonGenerarVenta.setBackground(new java.awt.Color(204, 204, 255));
        jButtonGenerarVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-eliminar (1).png"))); // NOI18N
        jButtonGenerarVenta.setText("Cancelar Venta");

        jButtonGenerarVenta1.setBackground(new java.awt.Color(204, 204, 255));
        jButtonGenerarVenta1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGenerarVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/punto-de-venta.png"))); // NOI18N
        jButtonGenerarVenta1.setText("Generar Venta");
        jButtonGenerarVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarVenta1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Total");

        jButtonquitar.setBackground(new java.awt.Color(204, 204, 255));
        jButtonquitar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonquitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/quitar-del-carrito.png"))); // NOI18N
        jButtonquitar.setText("Quitar");
        jButtonquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonquitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonGenerarVenta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonquitar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGenerarVenta1)
                    .addComponent(jButtonGenerarVenta)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonquitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Venta", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Fecha Venta");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Numero Factura");

        jTextFecha_Venta.setBackground(new java.awt.Color(204, 204, 255));
        jTextFecha_Venta.setBorder(null);
        jTextFecha_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFecha_VentaActionPerformed(evt);
            }
        });

        jlabelFechaVent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextID_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelFechaVent, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFecha_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelFechaVent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextID_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(44, 44, 44)
                .addComponent(jTextFecha_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreClienteActionPerformed

    private void jTextNombreproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreproActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
    jDialogCliente.setVisible(true);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jBDagregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDagregarProductoActionPerformed
       int fila = this.jTablebuscarproducto.getSelectedRow();
       if (fila == -1) {
         JOptionPane.showConfirmDialog(rootPane, "Seleccione un registro de la tabla");
         
       }else {
           try {
               int id = Integer.parseInt((String) this.jTablebuscarproducto.
                       getValueAt(fila, 0));
               String nom = (String) this.jTablebuscarproducto.
                       getValueAt(fila, 1);
               String Mode =(String) this.jTablebuscarproducto.
                       getValueAt(fila, 2);
               int sto = Integer.parseInt((String) this.jTablebuscarproducto.
                       getValueAt(fila,3 ));
               float prec_ve = Float.parseFloat((String) this.jTablebuscarproducto.
                       getValueAt(fila, 4).toString());
              int id_mar = Integer.parseInt((String) this.jTablebuscarproducto.
                       getValueAt(fila, 5));
               
               jTextID_Productos.setText("" + id);
               jTextNombrepro.setText(nom);
               jTextFieldStock.setText("" + sto);
                jTextFieldModelo.setText(Mode);
                jTextFieldPrecio_produ.setText("" + prec_ve);
               jTextFieldMarcas.setText("" + id_mar);
              
           }catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(rootPane,
                       "¡Ocurrio un ERROR " + e.getMessage());
           }
       }
    }//GEN-LAST:event_jBDagregarProductoActionPerformed

    private void jBDbuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDbuscarClienteActionPerformed
     if (jTextFieldBuscarCliente.getText().contentEquals("")) {
           JOptionPane.showMessageDialog(rootPane, "Ingrese texto a buscar");
           
       }else {
           try{
               
               String dato = jTextFieldBuscarCliente.getText();
               
               buscaDatosCliente(dato);
               jTextFieldBuscarCliente.setText("");
           }catch (SQLException ex) {
               Logger.getLogger(JInternalFrameMarca.class.getName()).log(Level.
                       SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_jBDbuscarClienteActionPerformed

    private void jBDagregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDagregarClienteActionPerformed
       int fila = this.jTableBuscaCliente.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro de la tabla");
        }else {
            
            try {
                int id = Integer.parseInt((String) this.jTableBuscaCliente.getValueAt(fila, 0));
                String nom = (String) this.jTableBuscaCliente.getValueAt(fila, 2);
                String ape = (String) this.jTableBuscaCliente.getValueAt(fila, 3);
                
                jTextID_Cliente.setText("" + id);
                jTextNombreCliente.setText(nom + " " + ape);
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane, "¡Ocurrio un ERROR! " + e.getMessage());
            }
            
        }
    }//GEN-LAST:event_jBDagregarClienteActionPerformed

    private void jButtonBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscar1ActionPerformed
    jDialogProducto.setVisible(true);
    }//GEN-LAST:event_jButtonBuscar1ActionPerformed

    private void jBDbuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDbuscarProductoActionPerformed
     if (jTextFieldBuscarProducto.getText().contentEquals("")) {
           JOptionPane.showMessageDialog(rootPane, "Ingrese texto a buscar");
           
       }else {
           try{
               
               String dato = jTextFieldBuscarProducto.getText();
               
               buscaDatosProducto(dato);
               jTextFieldBuscarProducto.setText("");
           }catch (SQLException ex) {
               Logger.getLogger(JInternalFrameDetalles_Venta.class.getName()).log(Level.
                       SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_jBDbuscarProductoActionPerformed

    private void jButAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButAgregarActionPerformed
     JInternalFrameCliente Clien = new JInternalFrameCliente();
MDIMoto_Repuestos.jDesktopPane1.add(Clien);
Clien.toFront();
Clien.setVisible(true);
     try {
         Clien.obtenerDatos();
     } catch (SQLException ex) {
         Logger.getLogger(JInternalFrameDetalles_Venta.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButAgregarActionPerformed

    private void jTextFieldMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcasActionPerformed

    private void jTextFieldModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModeloActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
      if (jTextFieldCantidadProducto.getText().contentEquals("0")) {
            JOptionPane.showMessageDialog(rootPane, 
                    "Escriba la cantidad de producto");
        }else {
            if(Integer.parseInt(jTextFieldCantidadProducto.getText().trim())>
                    Integer.parseInt(jTextFieldStock.getText())){
                JOptionPane.showMessageDialog(rootPane, 
                        "La cantidad indicada supera la existencia actual "
                + "del producto");
            }else {
                item += 1;
                
                objetoVentaTabla[0] = item;
                
                objetoVentaTabla[1] = jTextID_Productos.getText().trim();
                objetoVentaTabla[2] = jTextNombrepro.getText().trim();
                objetoVentaTabla[3] = jTextFieldModelo.getText().trim();
                objetoVentaTabla[4] = jTextFieldMarcas.getText().trim();
                objetoVentaTabla[5] = jTextFieldPrecio_produ.getText().trim();
                objetoVentaTabla[6] = jTextFieldCantidadProducto.getText().trim();
                
                Float subtotal = Float.parseFloat(jTextFieldCantidadProducto.getText().trim())
                        * Float.parseFloat(jTextFieldPrecio_produ.getText().trim());
                
                objetoVentaTabla[7] = subtotal;
                
                total += subtotal;
                
                jTextFieldTotal.setText(total.toString());
                
                modeloTablaVenta.addRow(objetoVentaTabla);
                
                limpiarCamposProductos();
            }
        }
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jTextFecha_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFecha_VentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFecha_VentaActionPerformed

    private void jButtonGenerarVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarVenta1ActionPerformed
      try {
          guardarVenta();
          
      }catch (SQLException ex){
          Logger.getLogger(JInternalFrameDetalles_Venta.class.getName()).
                  log(Level.SEVERE, null, ex);
      }

    }//GEN-LAST:event_jButtonGenerarVenta1ActionPerformed

    private void jButtonquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonquitarActionPerformed
       int fila = this.jTableVenta_Productos.getSelectedRow();
       if (fila == -1) {
           JOptionPane.showMessageDialog(rootPane, "Seleccione una fila a quitar de la tabla");
       }else{
           JDialog.setDefaultLookAndFeelDecorated(true);
           int resp = JOptionPane.showConfirmDialog(rootPane, 
                   "¿Estas seguro de quitar el producto de la lista" , 
                   "Aceptar", JOptionPane.YES_NO_OPTION,
                   JOptionPane.QUESTION_MESSAGE);
           if (resp == JOptionPane.NO_OPTION) {
              JOptionPane.showMessageDialog(rootPane, 
                      "No se ha retirado ningun producto");
           }else {
            if (resp == JOptionPane.YES_OPTION) {
                DefaultTableModel temp = (DefaultTableModel)
                        jTableVenta_Productos.getModel();
                temp.removeRow(fila);
                for (int i = 0; i < jTableVenta_Productos.getRowCount(); i++) {
                    total=0.0; 
                    total += Double.parseDouble(jTableVenta_Productos.getValueAt(i, 7).toString());
                    
                }
                jTextFieldTotal.setText(total.toString());
            }   
           }
           if (resp == JOptionPane.CLOSED_OPTION) {
               JOptionPane.showMessageDialog(rootPane, "Ninguna accion realizada");
           }
       }
    }//GEN-LAST:event_jButtonquitarActionPerformed

    private void jBVerventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVerventaActionPerformed
      DAODetalle_Venta daoventa = new  DAODetalle_Venta();
      
        if(id_venta!=0){
            try {
                
                
                daoventa.reporteFacturaVenta(id_venta);
                
            }catch (JRException ex) {
                Logger.getLogger(JInternalFrameDetalles_Venta.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, 
                    "No tiene productos, no se puede mostrar la factura");
        }
    }//GEN-LAST:event_jBVerventaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBDagregarCliente;
    private javax.swing.JButton jBDagregarProducto;
    private javax.swing.JButton jBDbuscarCliente;
    private javax.swing.JButton jBDbuscarProducto;
    private javax.swing.JButton jBVerventa;
    private javax.swing.JButton jButAgregar;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonBuscar1;
    private javax.swing.JButton jButtonGenerarVenta;
    private javax.swing.JButton jButtonGenerarVenta1;
    private javax.swing.JButton jButtonquitar;
    private javax.swing.JDialog jDialogCliente;
    private javax.swing.JDialog jDialogProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBuscaCliente;
    private javax.swing.JTable jTableVenta_Productos;
    private javax.swing.JTable jTablebuscarproducto;
    private javax.swing.JTextField jTextFecha_Venta;
    private javax.swing.JTextField jTextFieldBuscarCliente;
    private javax.swing.JTextField jTextFieldBuscarProducto;
    private javax.swing.JTextField jTextFieldCantidadProducto;
    private javax.swing.JTextField jTextFieldMarcas;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPrecio_produ;
    private javax.swing.JTextField jTextFieldStock;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextID_Cliente;
    private javax.swing.JTextField jTextID_Productos;
    private javax.swing.JTextField jTextID_Venta;
    private javax.swing.JTextField jTextNombreCliente;
    private javax.swing.JTextField jTextNombrepro;
    private javax.swing.JLabel jlabelFechaVent;
    // End of variables declaration//GEN-END:variables
}
