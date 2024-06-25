/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Conexion.Conexion;
import Modelo.Database;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.sql.Date;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

public class DAOCliente {
    
    Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "sp_LeerDatosCliente()";
        List<Map> registros = new Database().Listar(proced);
        List<Cliente> clientes = new ArrayList();
        
        for (Map registro : registros){
            Cliente aut = new Cliente((int)registro.get("ID_Cliente"),
                    (String) registro.get("Cédula"),
                    (String) registro.get("Nombre"),
                    (String) registro.get("Apellidos"),
                    (String) registro.get("Teléfono"));
            clientes.add(aut);
        }
        return clientes;
    }
    
     public int Insertar (Cliente aut)throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_InsertarDatosCliente(?,?,?,?)}");
            st.setString(1, aut.getCédula());
            st.setString(2, aut.getNombre());
            st.setString(3, aut.getApelllidos());
            st.setString(4, aut.getTeléfono());
            st.executeUpdate();
            
        }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
     
     public int Actualizar(Cliente aut) throws SQLException{
         try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_ModificarDatosCliente(?,?,?,?,?)}");
            st.setInt(1,aut.getID_Cliente());
            st.setString(2, aut.getCédula());
            st.setString(3,aut.getNombre());
            st.setString(4, aut.getApelllidos());
            st.setString(5, aut.getTeléfono());
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
                    prepareCall("{CALL sp_BorrarDatosCliente(?)}");
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
   
   public List busquedaCliente(String parametroBusqueda) throws  SQLException {
        ResultSet rs=null;
        List<Map> registros=null;
        List<Cliente> clientes = new ArrayList();
        
        List resultado=new ArrayList();
         
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_buscardatosClientes(?)}"); 
            
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs);
            registros =resultado;
            for (Map registro : registros){
                Cliente cli = new Cliente((int) registro.get("ID_Cliente"),
                (String) registro.get("Cédula"),
                (String) registro.get("Nombre"),
                (String) registro.get("Apellidos"),
                (String) registro.get("Teléfono"));
                clientes.add(cli);
            }
        }catch(SQLException e){
            System.out.println("No se realizo la consulta "+e.getMessage());
        }
            return clientes;
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
}
