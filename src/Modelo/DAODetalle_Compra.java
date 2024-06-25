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
public class DAODetalle_Compra {
     Conexion conectar=Conexion.getIntance();
        
    public List ObtenerDatos () throws SQLException {
        
        String proced = "LeerDatosDetalle_Compra()";
        List<Map> registros = new Database().Listar(proced);
        List<Detalle_Compra> Detalle_Compra = new ArrayList();
        
        for (Map registro : registros){
            Detalle_Compra aut = new Detalle_Compra((int)registro.get(" ID_Detalle_compra"),
            (int)registro.get("cantidad"),
            (float)registro.get("Precio_unitario"),
            (int)registro.get("ID_Compra"),
            (int)registro.get("ID_Producto "));
            Detalle_Compra.add(aut);
            
        }
        return Detalle_Compra;
    }
    
    public int InsertarDetalle_Compra (Detalle_Compra detall_Com)throws SQLException{
         try{
             CallableStatement st=conectar.conectar().
                    prepareCall("{CALL sp_InsertarDatosDetalle_C(?,?,?,?)}");
            st.setFloat(1, detall_Com.getCantidad());
            st.setFloat(2, detall_Com.getPrecio_unitario());
            st.setInt(3, detall_Com.getID_Compra());
            st.setInt(4, detall_Com.getID_Producto());
           
            st.executeUpdate();
         }catch (SQLException e){
            System.out.println(e+" Error ");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return -1;
    }
    
     public  void  reporteFacturaVenta(int ID_Compra)throws JRException{
         conectar.conectar();
         String path =  "C:\\Users\\admin\\Documents\\NetBeansProjects\\Moto_Repuestos_Leyton"
                 + "\\src\\Reporte\\Reporte_Venta_Letter_1.jrxml";
         JasperReport jr;
         Map parametro=new HashMap();
         
         parametro.put("ParameterCompra",ID_Compra);
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
