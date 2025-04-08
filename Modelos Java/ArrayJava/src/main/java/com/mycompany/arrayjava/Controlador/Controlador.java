/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrayjava.Controlador;

/**
 *
 * @author 57320
 */
import com.mycompany.arrayjava.Modelo.Modelo;
import com.mycompany.arrayjava.Vista.Vista;
import java.util.Scanner;

public class Controlador {
    private Modelo modelo = new Modelo();
    private Vista vista = new Vista();

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de políticos (mínimo 10): ");
        int n = sc.nextInt();
        sc.nextLine(); // Consumir newline

        if (n < 10) {
            System.out.println("El número mínimo de políticos es 10. Se establecerá n = 10 por defecto.");
            n = 10;
        }

        String metodo = vista.pedirMetodoOrdenamiento();

        modelo.generarPoliticos(n);
        vista.mostrarPoliticos(modelo.politicos, "\nLista de políticos generada:");

        modelo.ordenarPoliticos(metodo);
        vista.mostrarPoliticos(modelo.politicos, "\nLista de políticos ordenada por dinero robado:");
    }
}
