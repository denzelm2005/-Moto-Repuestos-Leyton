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
public class DAOMarca {
       Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "sp_LeerDatosMarca()";
        List<Map> registros = new Database().Listar(proced);
        List<Marca> Marca = new ArrayList();
        
        for (Map registro : registros){
            Marca aut = new Marca((int)registro.get("ID_Marca"),
                    (String) registro.get("Marca"));
            Marca.add(aut);
        }
        return Marca;
    }
     public int Insertar (Marca aut)throws SQLException{
         try{
             CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_InsertarDatosMarca(?)}");
            st.setString(1, aut.getMarca());
            st.executeUpdate();
         }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
     public int Actualizar(Marca aut) throws SQLException{
        
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_ModificarDatosMarca(?,?)}");
            st.setInt(1,aut.getID_Marca());
            st.setString(2, aut.getMarca());
            st.executeUpdate();
        }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
     }
      public  int Eliminar (int id) throws SQLException {
        try{
            
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_BorrarDatosMarca(?)}");
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


  
  




