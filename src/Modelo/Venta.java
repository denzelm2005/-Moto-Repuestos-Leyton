/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDateTime;
/**
 *
 * @author admin
 */
public class Venta {

    private int ID_Venta;
    private java.time.LocalDateTime Fecha_venta;
    private int ID_Cliente;

    private int ID_Detalle_venta;
    private float Precio_unitario;
    private int cantidad;
    private int ID_Producto;
    
    public Venta() {
    }
    
    
     public Venta(int ID_Venta, LocalDateTime Fecha_venta, 
        int ID_Cliente, int ID_Detalle_venta, float Precio_unitario, int cantidad, int ID_Producto) {
        this.ID_Venta = ID_Venta;
        this.Fecha_venta = Fecha_venta;
        this.ID_Cliente = ID_Cliente;
        this.ID_Detalle_venta = ID_Detalle_venta;
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
    }

    public Venta(LocalDateTime Fecha_venta, int ID_Cliente, float Precio_unitario, int cantidad, int ID_Producto) {
        this.Fecha_venta = Fecha_venta;
        this.ID_Cliente = ID_Cliente;
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
    }

    
    // Constructores de Venta
    public Venta(int ID_Cliente, LocalDateTime Fecha_venta) {
         this.ID_Cliente = ID_Cliente;
        this.Fecha_venta = Fecha_venta;
    }

    public Venta(int ID_Venta, float Precio_unitario, int cantidad, int ID_Producto) {
        this.ID_Venta = ID_Venta;
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
    }

    
    

    public Venta(int ID_Venta, LocalDateTime Fecha_venta, int ID_Cliente) {
        this.ID_Venta = ID_Venta;
        this.Fecha_venta = Fecha_venta;
        this.ID_Cliente = ID_Cliente;
    }

   
  
    
    public int getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(int ID_Venta) {
        this.ID_Venta = ID_Venta;
    }

    public LocalDateTime getFecha_venta() {
        return Fecha_venta;
    }

    public void setFecha_venta(LocalDateTime Fecha_venta) {
        this.Fecha_venta = Fecha_venta;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }


}
