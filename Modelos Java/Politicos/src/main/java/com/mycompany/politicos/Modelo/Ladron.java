/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Modelo;

/**
 *
 * @author moral
 */
import java.time.LocalDate;

public class Ladron {
    private int id;
    private int dineroRobado;
    private LocalDate fechaNacimiento;

    public Ladron(int id, int dineroRobado, LocalDate fechaNacimiento) {
        this.id = id;
        this.dineroRobado = dineroRobado;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public int getDineroRobado() {
        return dineroRobado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Robado: " + dineroRobado + ", Nacimiento: " + fechaNacimiento;
    }
}
