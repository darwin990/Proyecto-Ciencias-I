/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.matrizjava.Controlador;

/**
 *
 * @author 57320
 */
import com.mycompany.matrizjava.Modelo.Modelo;
import com.mycompany.matrizjava.Vista.Vista;
import java.util.Scanner;

public class Controlador {
    private Modelo modelo = new Modelo();
    private Vista vista = new Vista();

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de políticos: ");
        int n = sc.nextInt();

        if (n < 10) {
            System.out.println("El número mínimo es 10. Se asignará n = 10 por defecto.");
            n = 10;
        }

        System.out.println("Seleccione el algoritmo de ordenamiento:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.print("Opción: ");
        int algoritmo = sc.nextInt();
        if (algoritmo != 1 && algoritmo != 2) {
            System.out.println("Opción inválida. Se usará Bubble Sort por defecto.");
            algoritmo = 1;
        }

        modelo.generarPoliticos(n);
        vista.mostrarMatriz(modelo.matrizPoliticos, "Matriz de políticos generada:");

        modelo.ordenarPorEdad(algoritmo);
        vista.mostrarMatriz(modelo.matrizPoliticos, "Filas ordenadas por edad:");

        modelo.ordenarPorDineroYEdad(algoritmo);
        vista.mostrarMatriz(modelo.matrizPoliticos, "Filas por dinero y columnas por edad:");
    }
}

