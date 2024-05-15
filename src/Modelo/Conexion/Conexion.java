/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class Conexion {
       //Cadena de conexion de la bd SQL
    private static final  String URL="jdbc:sqlserver://localhost:1433;"
            + "databaseName=Moto_Repuestos_Leyton;"+"integratedSecurity=true;"+
            "encrypt=true;trustServerCertificate=true";
    
    private static Conexion instancia=null;
    private static Connection conex=null;
    
    private Conexion (){}
    
    public Connection conectar(){
        try{
            conex=DriverManager.getConnection(URL);
            System.out.println("Conexion Establecida");
            return conex;
        }catch(SQLException e){
            System.out.println("Error de conexion" + e);
        }
        return conex;
    }
    public void cerrarConexion() throws SQLException{
        try{
              conex.close();
        System.out.println("Conexion Cerrada");
        }catch(SQLException e){
          System.out.println("Error al cerrar la conexion"+ e);
          conex.close();
        } 
    }
    public static  Conexion getIntance(){
        if(instancia== null){
            instancia=new Conexion();
        }
        return instancia;
    }
}
