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
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.swing.JOptionPane;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;




/**
 *
 * @author admin
 */
public class DAODetalle_Venta {
    
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException{
        
        String proced = "LeerDatosDetalle_Venta()";
        List<Map> registros  = new Database().Listar(proced);
        List<Detalle_Venta> Detalle_Venta = new ArrayList();
        
        for (Map registro : registros){
            Detalle_Venta aut = new Detalle_Venta((int)registro.get("ID_Detalle_Venta"),
            (int)registro.get(" Precio_unitario"),
            (int)registro.get(" cantidad"),
             (int)registro.get("ID_Producto "),
              (int)registro.get("ID_Venta "));      
            Detalle_Venta.add(aut);
        }
        return Detalle_Venta;
    }
   
     public int InsertarDetalle_Venta (Detalle_Venta detall_ve)throws SQLException{
         try{
             CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_InsertarDatosDetalle_V(?,?,?,?)}");
            st.setFloat(1, detall_ve.getPrecio_unitario());
            st.setInt(2, detall_ve.getCantidad());
            st.setInt(3, detall_ve.getID_Producto());
            st.setInt(4, detall_ve.getID_Venta());
           
            st.executeUpdate();
         }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return -1;
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
     
     public  void  reporteFacturaVenta(int ID_Venta)throws JRException{
         conectar.conectar();
         String path = "C:\\Users\\admin\\Documents\\NetBeansProjects\\Moto_Repuestos_Leyton"
                 + "\\src\\Reporte\\ReportesVentas1.jrxml" ;
         JasperReport jr;
         Map parametro=new HashMap();
         
         parametro.put("ParameterVenta",ID_Venta);
         try {
         jr = JasperCompileManager.compileReport(path);
         
         JasperPrint mostrarReporte = JasperFillManager.fillReport(jr,
                 parametro, conectar.conectar());
         
         JasperViewer.viewReport(mostrarReporte, false);
         
     }catch (JRException e) {
         
       JOptionPane.showMessageDialog(null, e);
       System.out.println("Error "+e);
         
         
     }
     } 
}
     

