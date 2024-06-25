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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Database {
      Conexion Conectar=Conexion.getIntance();
 
    public  List Listar(String procedimiento)throws SQLException{
        ResultSet rs=null;
        List resultado=new ArrayList();
        try{
            CallableStatement st=Conectar.conectar().
                    prepareCall("{CALL "+procedimiento+"}");
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs);
        }catch(SQLException e){
            System.out.println("No se realizo la consulta: "+e.getMessage());
        }
        return resultado;
                
    }
    private List OrganizarDatos(ResultSet rs){
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
    
    public List<Map<String, Object>> listarClienteBusqueda(String procedimiento, String termino) {
        ResultSet rs = null;
        List<Map<String, Object>> resultados = new ArrayList<>();
        try (CallableStatement statement = Conectar.conectar().prepareCall(procedimiento)) {
            statement.setString(1, termino); // Configurar el parámetro de búsqueda
            rs = statement.executeQuery();
            resultados = OrganizarDatos(rs);
        } catch (SQLException e) {
            System.out.println("Error al realizar la búsqueda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultados;

}
 public List<Map<String, Object>> listarAutoresConBusqueda(String procedimiento, String termino) {
        ResultSet rs = null;
        List<Map<String, Object>> resultados = new ArrayList<>();
        try (CallableStatement statement = Conectar.conectar().prepareCall(procedimiento)) {
            statement.setString(1, termino); // Configurar el parámetro de búsqueda
            rs = statement.executeQuery();
            resultados = OrganizarDatos(rs);
        } catch (SQLException e) {
            System.out.println("Error al realizar la búsqueda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultados;
 }
 
    
    
}
