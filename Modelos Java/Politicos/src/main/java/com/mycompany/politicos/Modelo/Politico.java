/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Modelo;

/**
 *
 * @author 57320
 */
import java.time.LocalDate;
import java.time.Period;

public class Politico {
    private int id;
    private int dineroRobado; // en millones de rubros
    private LocalDate fechaNacimiento;

    public Politico(int id, int dineroRobado, LocalDate fechaNacimiento) {
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

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Dinero robado: " + dineroRobado +
               ", Edad: " + getEdad() +
               ", Fecha de nacimiento: " + fechaNacimiento;
    }
}
