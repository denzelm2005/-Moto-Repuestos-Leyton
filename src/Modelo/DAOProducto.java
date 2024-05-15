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
import java.sql.Date;

/**
 *
 * @author admin
 */
public class DAOProducto {
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "LeerDatosProducto()";
        List<Map> registros = new Database().Listar(proced);
        List<Producto> productos = new ArrayList();
        
        for (Map registro : registros){
            Producto aut = new Producto((int)registro.get("ID_Producto"),
                    (String) registro.get("Nombre"),
                    (String) registro.get("Modelo"),
                    (int) registro.get("Stock"),
                    (float) registro.get("Stock"),
                    (int) registro.get("ID_Marca"));
            productos.add(aut);
        }
        return productos;
    }
}
