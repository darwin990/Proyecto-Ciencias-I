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

public class BubbleSort<T> implements AlgoritmoOrdenamiento<T> {
    private int iteraciones;

    @Override
    public int ordenar(T[] arreglo, Comparator<T> comparador) {
        iteraciones = 0;
        int n = arreglo.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                iteraciones++; // comparación
                if (comparador.compare(arreglo[j], arreglo[j + 1]) > 0) {
                    // swap
                    T temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    iteraciones++; // intercambio
                    swapped = true;
                }
            }
            if (!swapped) break; // optimización: si no hubo swaps, está ordenado
        }

        return iteraciones;
    }
}
