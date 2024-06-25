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
import java.sql.ResultSet;
import java.util.HashMap;
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
    
    public List busquedaProveedor(String parametroBusqueda) throws  SQLException {
        ResultSet rs=null;
        List<Map> registros=null;
        List<Proveedor> pro = new ArrayList();
        
        List resultado=new ArrayList();
         
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_buscarproveedor(?)}"); 
            
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs);
            registros =resultado;
            for (Map registro : registros){
                Proveedor prov = new Proveedor ((int)registro.get("ID_Prov"),
                (String)registro.get("Nombre"),
                (String)registro.get("Teléfono"),
                (String)registro.get("Empresa")); 
                pro.add(prov);
            }
        }catch(SQLException e){
            System.out.println("No se realizo la consulta "+e.getMessage());
        }
            return pro;
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


