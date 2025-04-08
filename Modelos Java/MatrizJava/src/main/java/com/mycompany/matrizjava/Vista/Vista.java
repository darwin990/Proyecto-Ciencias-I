/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.matrizjava.Vista;

/**
 *
 * @author 57320
 */
import com.mycompany.matrizjava.Modelo.Politico;
import java.util.ArrayList;

public class Vista {

    public void mostrarMatriz(ArrayList<ArrayList<Politico>> matriz, String mensaje) {
        System.out.println(mensaje);
        for (ArrayList<Politico> fila : matriz) {
            for (Politico p : fila) {
                if (p.id == -1) {
                    System.out.print("[ X ] ");
                } else {
                    System.out.print("[" + p.id + "|$" + p.dineroRobado + "|" + p.edad + "] ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
}
