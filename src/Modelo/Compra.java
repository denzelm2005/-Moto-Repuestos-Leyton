/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalDateTime;
import java.sql.Time;

/**
 *
 * @author admin
 */
public class Compra {
  private int ID_Compra;
  private java.time.LocalDateTime Fecha_compra;
  private int ID_Prov;

    public Compra(int ID_Compra, LocalDateTime Fecha_compra, int ID_Prov) {
        this.ID_Compra = ID_Compra;
        this.Fecha_compra = Fecha_compra;
        this.ID_Prov = ID_Prov;
    }

    public Compra(LocalDateTime Fecha_compra, int ID_Prov) {
        this.Fecha_compra = Fecha_compra;
        this.ID_Prov = ID_Prov;
    }

    public int getID_Compra() {
        return ID_Compra;
    }

    public void setID_Compra(int ID_Compra) {
        this.ID_Compra = ID_Compra;
    }

    public LocalDateTime getFecha_compra() {
        return Fecha_compra;
    }

    public void setFecha_compra(LocalDateTime Fecha_compra) {
        this.Fecha_compra = Fecha_compra;
    }

    public int getID_Prov() {
        return ID_Prov;
    }

    public void setID_Prov(int ID_Prov) {
        this.ID_Prov = ID_Prov;
    }

  
}
