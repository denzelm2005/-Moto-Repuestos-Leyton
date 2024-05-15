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
public class DAOProveedor {
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "sp_LeerDatosProveedor()";
        List<Map> registros = new Database().Listar(proced);
        List<Proveedor> proveedores = new ArrayList();
        
        for (Map registro : registros){
            Proveedor aut = new Proveedor((int)registro.get("ID_Prov"),
                    (String) registro.get("Nombre"),
                    (String) registro.get("Teléfono"),
                    (String) registro.get("Empresa"));
            proveedores.add(aut);
        }
        return proveedores;
    }
    
     public int Insertar (Proveedor aut)throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_InsertarDatosProveedor(?,?,?)}");
            st.setString(1, aut.getNombre());
            st.setString(2, aut.getTeléfono());
            st.setString(3, aut.getEmpresa());
            st.executeUpdate();
            
        }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
     
     public int Actualizar(Proveedor aut) throws SQLException{
        
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_ModificarDatosProveedor(?,?,?,?)}");
            st.setInt(1,aut.getID_Prov());
            st.setString(2, aut.getNombre());
            st.setString(3, aut.getTeléfono());
            st.setString(4,aut.getEmpresa());
            
            st.executeUpdate();
        }catch (SQLException e){
            System.out.println(e+ " Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
     } 
     
     public  int Eliminar (int id) throws SQLException {
        try{
            
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_BorrarDatosProveedor(?)}");
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
}
