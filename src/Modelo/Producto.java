/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Marca;
/**
 *
 * @author admin
 */
public class Producto {
    private int ID_Producto;
    private String Nombre;
    private String Modelo;
    private int Stock;
    private float Precio_Venta;
    private float Precio_Compra;
    private int ID_Marca; 

    public Producto(int ID_Producto, String Nombre, String Modelo, int Stock, float Precio_Venta, float Precio_Compra, int ID_Marca) {
        this.ID_Producto = ID_Producto;
        this.Nombre = Nombre;
        this.Modelo = Modelo;
        this.Stock = Stock;
        this.Precio_Venta = Precio_Venta;
        this.Precio_Compra = Precio_Compra;
        this.ID_Marca = ID_Marca;
    }

    public Producto(String Nombre, String Modelo, int Stock, float Precio_Venta, float Precio_Compra, int ID_Marca) {
        this.Nombre = Nombre;
        this.Modelo = Modelo;
        this.Stock = Stock;
        this.Precio_Venta = Precio_Venta;
        this.Precio_Compra = Precio_Compra;
        this.ID_Marca = ID_Marca;
    }

    public Producto(int ID_Producto, int Stock) {
        this.ID_Producto = ID_Producto;
        this.Stock = Stock;
    }
    
    

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public float getPrecio_Venta() {
        return Precio_Venta;
    }

    public void setPrecio_Venta(float Precio_Venta) {
        this.Precio_Venta = Precio_Venta;
    }

    public float getPrecio_Compra() {
        return Precio_Compra;
    }

    public void setPrecio_Compra(float Precio_Compra) {
        this.Precio_Compra = Precio_Compra;
    }

    public int getID_Marca() {
        return ID_Marca;
    }

    public void setID_Marca(int ID_Marca) {
        this.ID_Marca = ID_Marca;
    }


}
    


