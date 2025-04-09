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

public class MergeSort<T> implements AlgoritmoOrdenamiento<T> {
    private int iteraciones;

    @Override
    public int ordenar(T[] arreglo, Comparator<T> comparador) {
        iteraciones = 0;
        mergeSort(arreglo, 0, arreglo.length - 1, comparador);
        return iteraciones;
    }

    private void mergeSort(T[] arr, int izquierda, int derecha, Comparator<T> comparador) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergeSort(arr, izquierda, medio, comparador);
            mergeSort(arr, medio + 1, derecha, comparador);
            merge(arr, izquierda, medio, derecha, comparador);
        }
    }

    private void merge(T[] arr, int izquierda, int medio, int derecha, Comparator<T> comparador) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        T[] izquierdaArr = (T[]) new Object[n1];
        T[] derechaArr = (T[]) new Object[n2];

        for (int i = 0; i < n1; ++i)
            izquierdaArr[i] = arr[izquierda + i];
        for (int j = 0; j < n2; ++j)
            derechaArr[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = izquierda;
        while (i < n1 && j < n2) {
            iteraciones++; // comparación
            if (comparador.compare(izquierdaArr[i], derechaArr[j]) <= 0) {
                arr[k] = izquierdaArr[i];
                i++;
            } else {
                arr[k] = derechaArr[j];
                j++;
            }
            iteraciones++; // asignación
            k++;
        }

        while (i < n1) {
            arr[k] = izquierdaArr[i];
            i++;
            k++;
            iteraciones++;
        }

        while (j < n2) {
            arr[k] = derechaArr[j];
            j++;
            k++;
            iteraciones++;
        }
    }
}
