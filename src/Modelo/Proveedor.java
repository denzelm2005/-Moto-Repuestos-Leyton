/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author admin
 */
public class Proveedor {
   
private int ID_Prov;
private String Nombre;
private String Teléfono;
private String Empresa;

    public Proveedor(int ID_Prov, String Nombre, String Teléfono, String Empresa) {
        this.ID_Prov = ID_Prov;
        this.Nombre = Nombre;
        this.Teléfono = Teléfono;
        this.Empresa = Empresa;
    }

    public Proveedor(String Nombre, String Teléfono, String Empresa) {
        this.Nombre = Nombre;
        this.Teléfono = Teléfono;
        this.Empresa = Empresa;
    }

    public int getID_Prov() {
        return ID_Prov;
    }

    public void setID_Prov(int ID_Prov) {
        this.ID_Prov = ID_Prov;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTeléfono() {
        return Teléfono;
    }

    public void setTeléfono(String Teléfono) {
        this.Teléfono = Teléfono;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }


  
}
