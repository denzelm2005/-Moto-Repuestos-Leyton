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

    public Venta(int ID_Venta, LocalDateTime Fecha_venta, int ID_Cliente) {
        this.ID_Venta = ID_Venta;
        this.Fecha_venta = Fecha_venta;
        this.ID_Cliente = ID_Cliente;
    }

    public Venta(LocalDateTime Fecha_venta, int ID_Cliente) {
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
