/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Controlador;

/**
 *
 * @author 57320
 */
import com.mycompany.politicos.Modelo.Modelo;
import com.mycompany.politicos.Vista.Vista;
import java.util.Scanner;

public class Controlador {
    private Modelo modelo = new Modelo();
    private Vista vista = new Vista();

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int n, estructura, algoritmo;

        System.out.print("Ingrese el numero de politicos (minimo 10): ");
        n = scanner.nextInt();
        if (n < 10) {
            System.out.println("Se asignara n = 10 por defecto.");
            n = 10;
        }

        System.out.print("Seleccione la estructura de datos:\n1. Array\n2. Matriz\nOpcion: ");
        estructura = scanner.nextInt();

        System.out.print("Seleccione el algoritmo de ordenamiento:\n1. Bubble Sort\n2. Selection Sort\nOpcion: ");
        algoritmo = scanner.nextInt();

        if (algoritmo != 1 && algoritmo != 2) {
            System.out.println("Opcion invalida. Se usara Bubble Sort por defecto.");
            algoritmo = 1;
        }

        if (estructura == 1) {
            modelo.generarArray(n);
            vista.mostrarArray(modelo.arrayPoliticos, "\nArray de politicos generado:");
            modelo.ordenarArray(algoritmo);
            vista.mostrarArray(modelo.arrayPoliticos, "\nArray ordenado por dinero robado:");
        } else {
            modelo.generarMatriz(n);
            vista.mostrarMatriz(modelo.matrizPoliticos, "\nMatriz de politicos generada:");
            modelo.ordenarMatriz(algoritmo);
            vista.mostrarMatriz(modelo.matrizPoliticos, "\nMatriz ordenada con Filas por dinero robado y columnas por edad:");
        }
    }
}

