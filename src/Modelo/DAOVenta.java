/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;
/**
 *
 * @author admin
 */
public class DAOVenta {
    Conexion conectar=Conexion.getIntance();
    
   public List<Venta> ObtenerDatos() throws SQLException {
    String proced = "sp_LeerDatosVenta()";
    List<Map> registros = new Database().Listar(proced);
    List<Venta> ventas = new ArrayList<>();

    for (Map<String, Object> registro : registros) {
        int idVenta = (int) registro.get("ID_Venta");
        Timestamp fechaVentaTimestamp = (Timestamp) registro.get("Fecha_venta");
        LocalDateTime fechaVenta = fechaVentaTimestamp.toLocalDateTime(); // Convertir a LocalDateTime
        int idCliente = (int) registro.get("ID_Cliente");

        Venta venta = new Venta(idVenta, fechaVenta, idCliente);
        ventas.add(venta);
    }
    return ventas;
}
    
       public int Insertar(Venta venta) throws SQLException {
    try {
        // Crear una instancia de LocalDateTime para obtener la fecha y hora actuales
        LocalDateTime now = LocalDateTime.now();
        
        // Preparar la llamada al procedimiento almacenado con los parámetros necesarios
        CallableStatement st = conectar.conectar().prepareCall("{CALL sp_InsertarVenta(?,?,?)}");
        
        // Establecer la fecha y hora actual como el primer parámetro
        st.setTimestamp(1, java.sql.Timestamp.valueOf(now));
        
        // Establecer el ID del cliente como el segundo parámetro
        st.setInt(2, venta.getID_Cliente());
        
        // Ejecutar la llamada al procedimiento almacenado
        st.executeUpdate();
        
    } catch (SQLException e) {
        // Manejo de excepciones
        System.out.println(e + " Error ");
        conectar.cerrarConexion();
        return -1;
    }
    
    // Cerrar la conexión después de la operación
    conectar.cerrarConexion();
    return 0;
}
       
    
 
}
