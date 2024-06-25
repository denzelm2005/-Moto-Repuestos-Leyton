/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author admin
 */
public class Detalle_Venta {
    private int ID_Detalle_venta;
    private float Precio_unitario;
    private int cantidad;
    private int ID_Producto;
    private int ID_Venta;
    
    public Detalle_Venta(int ID_Venta, float Precio_unitario, int cantidad, int ID_Producto) {
        this.ID_Venta = ID_Venta;
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
    }

    public int getID_Detalle_venta() {
        return ID_Detalle_venta;
    }

    public Detalle_Venta(float Precio_unitario, int cantidad, int ID_Producto) {
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
    }
    
    

    public void setID_Detalle_venta(int ID_Detalle_venta) {
        this.ID_Detalle_venta = ID_Detalle_venta;
    }

    public float getPrecio_unitario() {
        return Precio_unitario;
    }

    public void setPrecio_unitario(float Precio_unitario) {
        this.Precio_unitario = Precio_unitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(int ID_Venta) {
        this.ID_Venta = ID_Venta;
    }

    public Detalle_Venta(float Precio_unitario, int cantidad, int ID_Producto, int ID_Venta) {
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
        this.ID_Venta = ID_Venta;
    }

    public Detalle_Venta(int ID_Detalle_venta, float Precio_unitario, int cantidad, int ID_Producto, int ID_Venta) {
        this.ID_Detalle_venta = ID_Detalle_venta;
        this.Precio_unitario = Precio_unitario;
        this.cantidad = cantidad;
        this.ID_Producto = ID_Producto;
        this.ID_Venta = ID_Venta;
    }
    
    
    
    
}
