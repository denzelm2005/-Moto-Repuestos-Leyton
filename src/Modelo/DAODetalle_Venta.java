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
/**
 *
 * @author admin
 */
public class DAODetalle_Venta {
    
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException{
        
        String proced = "LeerDatosDetalle_Venta()";
        List<Map> registros  = new Database().Listar(proced);
        List<Detalle_Venta> Detalle_Venta = new ArrayList();
        
        for (Map registro : registros){
            Detalle_Venta aut = new Detalle_Venta((int)registro.get("ID_Detalle_Venta"),
            (int)registro.get(" Precio_unitario"),
            (int)registro.get(" cantidad"),
             (int)registro.get("ID_Producto "),
              (int)registro.get("ID_Venta "));      
            Detalle_Venta.add(aut);
        }
        return Detalle_Venta;
    }
    
}
