/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author admin
 */
public class Cliente {

private int ID_Cliente;    
private String Cédula;
private String Nombre;
private String Apelllidos;
private String Teléfono;

    public Cliente(int ID_Cliente, String Cédula, String Nombre, String Apelllidos, String Teléfono) {
        this.ID_Cliente = ID_Cliente;
        this.Cédula = Cédula;
        this.Nombre = Nombre;
        this.Apelllidos = Apelllidos;
        this.Teléfono = Teléfono;
    }

    public Cliente(String Cédula, String Nombre, String Apelllidos, String Teléfono) {
        this.Cédula = Cédula;
        this.Nombre = Nombre;
        this.Apelllidos = Apelllidos;
        this.Teléfono = Teléfono;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getCédula() {
        return Cédula;
    }

    public void setCédula(String Cédula) {
        this.Cédula = Cédula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApelllidos() {
        return Apelllidos;
    }

    public void setApelllidos(String Apelllidos) {
        this.Apelllidos = Apelllidos;
    }

    public String getTeléfono() {
        return Teléfono;
    }

    public void setTeléfono(String Teléfono) {
        this.Teléfono = Teléfono;
    }



}


