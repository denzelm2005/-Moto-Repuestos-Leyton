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
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class DAOProducto {
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "sp_LeerDatosProducto()";
        List<Map> registros = new Database().Listar(proced);
        List<Producto> productos = new ArrayList();
        
        for (Map registro : registros){
            Producto aut = new Producto((int)registro.get("ID_Producto"),
                    (String) registro.get("Nombre"),
                    (String) registro.get("Modelo"),
                    (int) registro.get("Stock"),
                    (float) registro.get("Precio_Venta"),
                    (float) registro.get("Precio_Compra"),
                    (int) registro.get("ID_Marca"));
            productos.add(aut);
        }
        return productos;
    }
         public int Insertar (Producto aut)throws SQLException{
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_InsertarDatosProducto(?,?,?,?,?,?)}");
            st.setString(1, aut.getNombre());
            st.setString(2, aut.getModelo());
            st.setFloat(3, aut.getPrecio_Venta());
            st.setFloat(4, aut.getPrecio_Compra());
            st.setInt(5, aut.getStock());
             st.setInt(6, aut.getID_Marca());

            st.executeUpdate();
            
        }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
         
           public int Actualizar(Producto aut) throws SQLException{
         try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_ModificarDatosProducto(?,?,?,?,?,?,?)}");
            st.setInt(1,aut.getID_Producto());
            st.setString(2, aut.getNombre());
            st.setString(3,aut.getModelo());
            st.setFloat(4, aut.getPrecio_Venta());
            st.setFloat(5, aut.getPrecio_Compra());
            st.setInt(6,aut.getStock());
            st.setInt(7,aut.getID_Marca());
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
                    prepareCall("{CALL sp_BorrarDatosProducto(?)}");
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
       
         public List busquedaProducto(String parametroBusqueda) throws  SQLException {
        ResultSet rs=null;
        List<Map> registros=null;
        List<Producto> productos = new ArrayList();
        
        List resultado=new ArrayList();
         
        try{
            CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_buscardatosProductos(?)}"); 
            
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado=OrganizarDatos(rs);
            registros =resultado;
            for (Map registro : registros){
                Producto pro = new Producto((int) registro.get("ID_Producto"),
               (String) registro.get("Nombre"),
                    (String) registro.get("Modelo"),
                    (int) registro.get("Stock"),
                    (float) registro.get("Precio_Venta"),
                    (float) registro.get("Precio_Compra"),
                    (int) registro.get("ID_Marca"));
                productos.add(pro);
            }
        }catch(SQLException e){
            System.out.println("No se realizo la consulta "+e.getMessage());
        }
            return productos;
    }    
      
        public void productoExistenciaBaja() throws JRException {
        conectar.conectar();
        
        String path = "C:\\Users\\admin\\Documents\\NetBeansProjects\\"
                + "Moto_Repuestos_Leyton\\src\\Reporte\\Producto_categoria1.jrxml";
        JasperReport jr;
        
        try{
          jr = JasperCompileManager.compileReport(path);
          JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, 
                  null, conectar.conectar());
          
          JasperViewer.viewReport(mostrarReporte, false);
          
        }catch(JRException e){
            JOptionPane.showMessageDialog(null, e);
                                     System.out.println("Error "+e);
        }
     }
       
     public void productosVendidos() throws JRException {
        conectar.conectar();
        
        String path = "C:\\Users\\admin\\Documents\\NetBeansProjects\\"
                + "Moto_Repuestos_Leyton\\src\\Reporte\\Productos_vendidos.jrxml";
        JasperReport jr;
        
        try{
          jr = JasperCompileManager.compileReport(path);
          JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, 
                  null, conectar.conectar());
          
          JasperViewer.viewReport(mostrarReporte, false);
          
        }catch(JRException e){
            JOptionPane.showMessageDialog(null, e);
                                     System.out.println("Error "+e);
        }
     }
}
