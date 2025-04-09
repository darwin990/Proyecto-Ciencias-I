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

public class QuickSort<T> implements AlgoritmoOrdenamiento<T> {
    private int iteraciones;

    @Override
    public int ordenar(T[] arreglo, Comparator<T> comparador) {
        iteraciones = 0;
        quickSort(arreglo, 0, arreglo.length - 1, comparador);
        return iteraciones;
    }

    private void quickSort(T[] arr, int low, int high, Comparator<T> comparador) {
        if (low < high) {
            int pi = partition(arr, low, high, comparador);
            quickSort(arr, low, pi - 1, comparador);
            quickSort(arr, pi + 1, high, comparador);
        }
    }

    private int partition(T[] arr, int low, int high, Comparator<T> comparador) {
        T pivot = arr[high];
        int i = (low - 1); // índice del menor elemento

        for (int j = low; j < high; j++) {
            iteraciones++; // comparación
            if (comparador.compare(arr[j], pivot) <= 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                iteraciones++; // intercambio
            }
        }

        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        iteraciones++; // intercambio final

        return i + 1;
    }
}
