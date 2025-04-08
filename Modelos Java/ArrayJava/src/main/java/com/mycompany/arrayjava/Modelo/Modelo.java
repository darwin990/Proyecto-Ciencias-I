/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrayjava.Modelo;

/**
 *
 * @author 57320
 */

import java.util.ArrayList;
import java.util.Random;

public class Modelo {
    public ArrayList<Politico> politicos = new ArrayList<>();

    public void generarPoliticos(int n) {
        Random rand = new Random();
        politicos.clear();
        for (int i = 0; i < n; ++i) {
            Politico p = new Politico();
            p.id = i + 1;
            p.dineroRobado = rand.nextInt(1000000) + 100;
            p.fechaNacimiento = generarFechaAleatoria(rand);
            politicos.add(p);
        }
    }

    public void ordenarPoliticos(String metodo) {
        if (metodo.equalsIgnoreCase("bubble")) {
            bubbleSort();
        } else if (metodo.equalsIgnoreCase("selection")) {
            selectionSort();
        } else {
            System.out.println("Metodo de ordenamiento invalido. Se usara Bubble Sort por defecto.");
            bubbleSort();
        }
    }

    private void bubbleSort() {
        boolean swapped;
        for (int i = 0; i < politicos.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < politicos.size() - i - 1; j++) {
                if (politicos.get(j).dineroRobado > politicos.get(j + 1).dineroRobado) {
                    Politico temp = politicos.get(j);
                    politicos.set(j, politicos.get(j + 1));
                    politicos.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private void selectionSort() {
        int n = politicos.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (politicos.get(j).dineroRobado < politicos.get(minIndex).dineroRobado) {
                    minIndex = j;
                }
            }
            Politico temp = politicos.get(i);
            politicos.set(i, politicos.get(minIndex));
            politicos.set(minIndex, temp);
        }
    }

    private String generarFechaAleatoria(Random rand) {
        int dia = rand.nextInt(28) + 1;
        int mes = rand.nextInt(12) + 1;
        int anio = rand.nextInt(50) + 1950;
        return dia + "/" + mes + "/" + anio;
    }
}
