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
public class DAOCompra {
        Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "LeerDatosCompra()";
        List<Map> registros = new Database().Listar(proced);
        List<Compra> Compra = new ArrayList();
        
        for (Map registro : registros){
            Compra aut = new Compra((int)registro.get("ID_Compra"),
                    (java.time.LocalDateTime) registro.get("Fecha_Compra"),
                    (int) registro.get("ID_prov"));
            Compra.add(aut);
        }
        return Compra;
    }
}
