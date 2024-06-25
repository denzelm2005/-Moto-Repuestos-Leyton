/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DAOVenta {
    Conexion conectar=Conexion.getIntance();
    
  private LocalDateTime convertirTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    // Método para formatear un LocalDateTime en una cadena con el formato "yyyy-MM-dd HH:mm"
    private String formatearFecha(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return fecha.format(formatter);
    }

  public List<Venta> obtenerDatos() throws SQLException {
    String procedimiento = "sp_LeerDatosVenta()";
    List<Map<String, Object>> registros;
    try {
        registros = new Database().Listar(procedimiento);
    } catch (SQLException ex) {
        // Manejar la excepción adecuadamente o propagarla si es necesario
        throw ex;
    }
    
    List<Venta> datosVentas = new ArrayList<>();
    for (Map<String, Object> registro : registros) {
        int idVenta = (int) registro.get("ID_Venta");
        Timestamp fechaVentaTimestamp = (Timestamp) registro.get("Fecha_venta");
        LocalDateTime fechaVenta = fechaVentaTimestamp.toLocalDateTime();
        int idCliente = (int) registro.get("ID_Cliente");

        Venta venta = new Venta(idVenta, fechaVenta, idCliente);
        datosVentas.add(venta);
    }
    // Cerrar la conexión a la base de datos u otros recursos aquí si es necesario
    
    return datosVentas;
}

public int Insertar(Venta venta) throws SQLException {
    try {
        // Preparar la llamada al procedimiento almacenado
        CallableStatement st = conectar.conectar().prepareCall("{CALL sp_InsertarDatosVenta(?,?)}");

        // Convertir LocalDateTime a Timestamp para el primer parámetro
        Timestamp fechaVentaTimestamp = Timestamp.valueOf(venta.getFecha_venta());
        st.setTimestamp(1, fechaVentaTimestamp);

        st.setInt(2, venta.getID_Cliente());

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

public int obtenerUltimoNumFactura() throws SQLException{
       int id_venta = 0;
       ResultSet rs = null;
       try {
           
           CallableStatement st =  conectar.conectar().
                   prepareCall("{CALL sp_obtenerUltimoIDVenta()}");
           
           rs = st.executeQuery();
           if(rs.next()) {
               id_venta = rs.getInt(1);
           }
           return id_venta;
       }catch (SQLException e) {
           System.out.println("No se realizo la consulta: " + e.getMessage());
           return 0;
       }
     
     }
     
       
      public int Actualizar(Venta venta) throws SQLException {
    try {
        // Preparar la llamada al procedimiento almacenado
        CallableStatement st = conectar.conectar().prepareCall("{CALL sp_ModificarDatosVenta(?,?,?)}");
        
        // Establecer los parámetros del procedimiento almacenado
        st.setInt(1, venta.getID_Venta());
        
        // Convertir LocalDateTime a Timestamp para el segundo parámetro
        Timestamp fechaVentaTimestamp = Timestamp.valueOf(venta.getFecha_venta());
        st.setTimestamp(2, fechaVentaTimestamp);
        
        st.setInt(3, venta.getID_Cliente());
        
        // Ejecutar la actualización
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
                    prepareCall("{CALL sp_BorrarDatosVenta(?)}");
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
    
       public List busquedaVentas(String parametroBusqueda) throws  SQLException {
        ResultSet rs=null;
        List<Map> registros=null;
        List<Venta> ventas = new ArrayList();
        
        List resultado=new ArrayList();
         
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_buscarventas(?)}"); 
            
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs);
            registros =resultado;
            for (Map registro : registros){
                Venta vent = new Venta((int) registro.get("ID_Venta"),
                (LocalDateTime) registro.get("Fecha_venta"));
                ventas.add(vent);
            }
        }catch(SQLException e){
            System.out.println("No se realizo la consulta "+e.getMessage());
        }
            return ventas;
    } 
 
       public List OrganizarDatos(ResultSet rs){
       List filas=new ArrayList();
       try{
           int numColumnas=rs.getMetaData().getColumnCount();
           while(rs.next()){
               Map<String, Object> renglon=new HashMap();
               for(int i=1; i<=numColumnas; i++){
                   String nombreCampo=rs.getMetaData().getColumnName(i);
                   Object valor=rs.getObject(nombreCampo);
                   renglon.put(nombreCampo, valor);
               }
               filas.add(renglon);
           }
                   
       }catch(SQLException e){
           System.out.println(e+"Error");
       }
       return filas;
    }
       
         public int actualizarExistenciaProductos(Producto pro) throws SQLException {
         
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
    
        public  void  reporteFactura(int ID_Venta)throws JRException{
         conectar.conectar();
         String path = "C:\\Users\\admin\\Documents\\NetBeansProjects\\Moto_Repuestos_Leyton"
                 + "\\src\\Reporte\\ReportesVentas1.jrxml" ;
         JasperReport jr;
         Map parametro=new HashMap();
         
         parametro.put("ParameterVenta", ID_Venta);
         try {
         jr = JasperCompileManager.compileReport(path);
         
         JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conectar.conectar());
         
         JasperViewer.viewReport(mostrarReporte, false);
         
     }catch (JRException e) {
         
       JOptionPane.showMessageDialog(null, e);
       System.out.println("Error "+e);
      }
     }    
     
        public void reporteVentasPorFecha(String fecha_inicio) throws JRException {
        try {
            // Ruta del archivo JRXML
            String path = "C:\\Users\\admin\\Documents\\NetBeansProjects\\Moto_Repuestos_Leyton\\src\\Reporte\\Ventas_X_dia.jrxml";

            // Compilar el reporte desde el archivo JRXML
            JasperReport jr = JasperCompileManager.compileReport(path);

            // Configurar los parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fecha_inicio", fecha_inicio);

            // Llenar el reporte con datos desde el procedimiento almacenado
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, 
                    parametros, conectar.conectar());

            // Mostrar el reporte en una ventana
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
         
        

