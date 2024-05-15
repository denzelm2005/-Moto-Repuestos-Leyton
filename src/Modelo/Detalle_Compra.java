/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author admin
 */
public class Detalle_Compra {
   private int ID_Detalle_compra;
   private int cantidad;
   private float Precio_unitario;
   private int ID_Compra;
   private int ID_Producto;

    public Detalle_Compra(int cantidad, float Precio_unitario, int ID_Compra, int ID_Producto) {
        this.cantidad = cantidad;
        this.Precio_unitario = Precio_unitario;
        this.ID_Compra = ID_Compra;
        this.ID_Producto = ID_Producto;
    }

    public Detalle_Compra(int ID_Detalle_compra, int cantidad, float Precio_unitario, int ID_Compra, int ID_Producto) {
        this.ID_Detalle_compra = ID_Detalle_compra;
        this.cantidad = cantidad;
        this.Precio_unitario = Precio_unitario;
        this.ID_Compra = ID_Compra;
        this.ID_Producto = ID_Producto;
    }

    public int getID_Detalle_compra() {
        return ID_Detalle_compra;
    }

    public void setID_Detalle_compra(int ID_Detalle_compra) {
        this.ID_Detalle_compra = ID_Detalle_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_unitario() {
        return Precio_unitario;
    }

    public void setPrecio_unitario(float Precio_unitario) {
        this.Precio_unitario = Precio_unitario;
    }

    public int getID_Compra() {
        return ID_Compra;
    }

    public void setID_Compra(int ID_Compra) {
        this.ID_Compra = ID_Compra;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }
    
    
}
