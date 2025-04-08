/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Modelo;

/**
 *
 * @author 57320
 */
public class Politico {
    public int id;
    public int dineroRobado;
    public String fechaNacimiento;
    public int edad;

    public Politico(int id, int dineroRobado, int edad, String fechaNacimiento) {
        this.id = id;
        this.dineroRobado = dineroRobado;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Politico() {
        this(-1, -1, -1, "X");
    }
}

