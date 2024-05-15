/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author admin
 */
public class Marca {
 private int ID_Marca;
 private String Marca;

    public Marca(int ID_Marca, String Marca) {
        this.ID_Marca = ID_Marca;
        this.Marca = Marca;
    }

    public Marca(String Marca) {
        this.Marca = Marca;
    }

    public int getID_Marca() {
        return ID_Marca;
    }

    public void setID_Marca(int ID_Marca) {
        this.ID_Marca = ID_Marca;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }
}
