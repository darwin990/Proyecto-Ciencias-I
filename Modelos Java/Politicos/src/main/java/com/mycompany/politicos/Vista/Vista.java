/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Vista;

/**
 *
 * @author 57320
 */
import com.mycompany.politicos.Modelo.Politico;

import java.util.List;

public class Vista {
    public void mostrarArray(List<Politico> lista, String mensaje) {
        System.out.println(mensaje);
        System.out.printf("%-5s %-15s %-10s %s\n", "ID", "Dinero Robado", "Edad", "Fecha");
        System.out.println("---------------------------------------------");
        for (Politico p : lista) {
            System.out.printf("%-5d %-15d %-10d %s\n", p.id, p.dineroRobado, p.edad, p.fechaNacimiento);
        }
        System.out.println("---------------------------------------------");
    }

    public void mostrarMatriz(List<List<Politico>> matriz, String mensaje) {
        System.out.println(mensaje);
        for (List<Politico> fila : matriz) {
            for (Politico p : fila) {
                if (p.id == -1) {
                    System.out.print("[ X ] ");
                } else {
                    System.out.printf("[%d|$%d|%d] ", p.id, p.dineroRobado, p.edad);
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
}

