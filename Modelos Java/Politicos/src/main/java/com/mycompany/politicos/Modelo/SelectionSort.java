/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Modelo;

/**
 *
 * @author moral
 */
import java.util.Comparator;

public class SelectionSort<T> implements AlgoritmoOrdenamiento<T> {
    private int iteraciones;

    @Override
    public int ordenar(T[] arreglo, Comparator<T> comparador) {
        iteraciones = 0;
        int n = arreglo.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                iteraciones++; // comparación
                if (comparador.compare(arreglo[j], arreglo[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            // swap
            if (minIdx != i) {
                T temp = arreglo[i];
                arreglo[i] = arreglo[minIdx];
                arreglo[minIdx] = temp;
                iteraciones++; // intercambio
            }
        }

        return iteraciones;
    }
}
