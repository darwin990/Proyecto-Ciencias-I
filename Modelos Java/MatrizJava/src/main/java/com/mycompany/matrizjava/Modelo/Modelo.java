/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.matrizjava.Modelo;

/**
 *
 * @author 57320
 */
import java.util.ArrayList;
import java.util.Random;

public class Modelo {
    public ArrayList<ArrayList<Politico>> matrizPoliticos = new ArrayList<>();
    public int k, m;

    public void generarPoliticos(int n) {
        matrizPoliticos.clear();
        Random rand = new Random();
        k = (int) Math.ceil(Math.sqrt(n));
        m = (int) Math.ceil((double) n / k);
        int total = k * m;

        ArrayList<Politico> politicos = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            Politico p = new Politico();
            p.id = i + 1;
            p.dineroRobado = rand.nextInt(1000000) + 100;
            p.edad = rand.nextInt(50) + 30;
            p.fechaNacimiento = String.valueOf(2024 - p.edad);
            politicos.add(p);
        }

        while (politicos.size() < total) {
            Politico p = new Politico();
            p.id = -1;
            p.dineroRobado = -1;
            p.edad = -1;
            p.fechaNacimiento = "X";
            politicos.add(p);
        }

        int index = 0;
        for (int i = 0; i < k; i++) {
            ArrayList<Politico> fila = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                fila.add(politicos.get(index++));
            }
            matrizPoliticos.add(fila);
        }
    }

    public void ordenarPorEdad(int algoritmo) {
        for (ArrayList<Politico> fila : matrizPoliticos) {
            if (algoritmo == 1)
                bubbleSort(fila, true);
            else
                selectionSort(fila, true);
        }
    }

    public void ordenarPorDineroYEdad(int algoritmo) {
        for (ArrayList<Politico> fila : matrizPoliticos) {
            if (algoritmo == 1)
                bubbleSort(fila, false);
            else
                selectionSort(fila, false);
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < k - 1; i++) {
                int minIndex = i;
                for (int l = i + 1; l < k; l++) {
                    if (matrizPoliticos.get(l).get(j).edad < matrizPoliticos.get(minIndex).get(j).edad) {
                        minIndex = l;
                    }
                }
                Politico temp = matrizPoliticos.get(i).get(j);
                matrizPoliticos.get(i).set(j, matrizPoliticos.get(minIndex).get(j));
                matrizPoliticos.get(minIndex).set(j, temp);
            }
        }
    }

    private void bubbleSort(ArrayList<Politico> fila, boolean ordenarPorEdad) {
        for (int i = 0; i < fila.size() - 1; i++) {
            for (int j = 0; j < fila.size() - i - 1; j++) {
                if ((ordenarPorEdad && fila.get(j).edad > fila.get(j + 1).edad) ||
                    (!ordenarPorEdad && fila.get(j).dineroRobado > fila.get(j + 1).dineroRobado)) {
                    Politico temp = fila.get(j);
                    fila.set(j, fila.get(j + 1));
                    fila.set(j + 1, temp);
                }
            }
        }
    }

    private void selectionSort(ArrayList<Politico> fila, boolean ordenarPorEdad) {
        for (int i = 0; i < fila.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < fila.size(); j++) {
                if ((ordenarPorEdad && fila.get(j).edad < fila.get(minIndex).edad) ||
                    (!ordenarPorEdad && fila.get(j).dineroRobado < fila.get(minIndex).dineroRobado)) {
                    minIndex = j;
                }
            }
            Politico temp = fila.get(i);
            fila.set(i, fila.get(minIndex));
            fila.set(minIndex, temp);
        }
    }
}
