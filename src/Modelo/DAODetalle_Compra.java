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
public class DAODetalle_Compra {
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "LeerDatosDetalle_Compra()";
        List<Map> registros = new Database().Listar(proced);
        List<Detalle_Compra> Detalle_Compra = new ArrayList();
        
        for (Map registro : registros){
            Detalle_Compra aut = new Detalle_Compra((int)registro.get(" ID_Detalle_compra"),
            (int)registro.get("cantidad"),
            (float)registro.get("Precio_unitario"),
            (int)registro.get("ID_Compra"),
            (int)registro.get("ID_Producto "));
            Detalle_Compra.add(aut);
            
        }
        return Detalle_Compra;
    }
    
    
}
