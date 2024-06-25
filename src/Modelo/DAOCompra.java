/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.JOptionPane;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author admin
 */
public class DAOCompra {
        Conexion conectar=Conexion.getIntance();
        private LocalDateTime convertirTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    // Método para formatear un LocalDateTime en una cadena con el formato "yyyy-MM-dd HH:mm"
    private String formatearFecha(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return fecha.format(formatter);
    }
       
     public List<Compra> obtenerDatos() throws SQLException {
    String procedimiento = "sp_LeerDatosCompra()";
    List<Map<String, Object>> registros;
    try {
        registros = new Database().Listar(procedimiento);
    } catch (SQLException ex) {
        // Manejar la excepción adecuadamente o propagarla si es necesario
        throw ex;
    }
    
    List<Compra> datosCompras = new ArrayList<>();
    for (Map<String, Object> registro : registros) {
        int idCompra = (int) registro.get("ID_Compra");
        Timestamp fechaVentaTimestamp = (Timestamp) registro.get("Fecha_compra");
        LocalDateTime fechaVenta = fechaVentaTimestamp.toLocalDateTime();
        int idProveedor = (int) registro.get("ID_Prov");

        Compra compra = new Compra(idCompra, fechaVenta, idProveedor);
        datosCompras.add(compra);
    }
    // Cerrar la conexión a la base de datos u otros recursos aquí si es necesario
    
    return datosCompras;
}
    
     public int InsertarCompra (Compra compra)throws SQLException{
           try {
        // Preparar la llamada al procedimiento almacenado
        CallableStatement st = conectar.conectar().prepareCall("{CALL sp_InsertarDatosCompra(?,?)}");

        // Convertir LocalDateTime a Timestamp para el primer parámetro
        Timestamp fechaCompraTimestamp = Timestamp.valueOf(compra.getFecha_compra());
        st.setTimestamp(1, fechaCompraTimestamp);

        st.setInt(2, compra.getID_Prov());

        // Ejecutar la inserción
        int filasAfectadas = st.executeUpdate();

        // Cerrar la conexión
        conectar.cerrarConexion();

        // Devolver el número de filas afectadas
        return filasAfectadas;
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        System.out.println(e + " Error ");
        // Cerrar la conexión en caso de error
        conectar.cerrarConexion();
        // Devolver un valor indicando un error (-1)
        return -1;
    }
}
     
     public  int Eliminar (int id) throws SQLException {
        try{
            
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_BorrarDatosCompra(?)}");
            st.setInt(1, id);
            st.executeUpdate();
            
        }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.conectar();
        return 0;
    }
     
     public int obtenerUltimoNumFactura_Compra() throws SQLException{
       int id_Compra = 0;
       ResultSet rs = null;
       try {
           
           CallableStatement st =  conectar.conectar().
                   prepareCall("{CALL sp_obtenerUltimoIDCompra()}");
           
           rs = st.executeQuery();
           if(rs.next()) {
               id_Compra = rs.getInt(1);
           }
           return id_Compra;
       }catch (SQLException e) {
           System.out.println("No se realizo la consulta: " + e.getMessage());
           return 0;
       }
     
     }
     
     public int actualizarExistenciaProductosCompra(Producto pro) throws SQLException {
         
         try {
             
             CallableStatement st = conectar.conectar().
                     prepareCall("{CALL sp_actualizaExistenciaPro(?,?)}");
             st.setInt(1, pro.getStock());
             st.setInt(2, pro.getID_Producto());
             st.executeUpdate();
             
             
             return 0;
         }catch (SQLException e) {
             System.out.println("No se realizo la consulta: " + e.getMessage());
             return -1;
         }
     }
     
      public  void  reporteFacturaVenta(int ID_Compra)throws JRException{
         conectar.conectar();
         String path = "C:\\Users\\admin\\Documents\\NetBeansProjects\\Moto_Repuestos_Leyton"
                 + "\\src\\Reporte\\Reporte_Venta_Letter_1.jrxml";
                 
         JasperReport jr;
         Map parametro=new HashMap();
         
         parametro.put("ParameterCompra",ID_Compra);
         try {
         jr = JasperCompileManager.compileReport(path);
         
         JasperPrint mostrarReporte = JasperFillManager.fillReport(jr,
                 parametro, conectar.conectar());
         
         JasperViewer.viewReport(mostrarReporte, false);
         
     }catch (JRException e) {
         
       JOptionPane.showMessageDialog(null, e);
       System.out.println("Error "+e);
         
         
     }
     } 
     
}
