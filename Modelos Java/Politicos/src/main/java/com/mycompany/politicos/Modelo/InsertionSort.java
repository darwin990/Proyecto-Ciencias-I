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

public class InsertionSort<T> implements AlgoritmoOrdenamiento<T> {
    private int iteraciones;

    @Override
    public int ordenar(T[] arreglo, Comparator<T> comparador) {
        iteraciones = 0;
        for (int i = 1; i < arreglo.length; i++) {
            T key = arreglo[i];
            int j = i - 1;

            while (j >= 0 && comparador.compare(arreglo[j], key) > 0) {
                arreglo[j + 1] = arreglo[j];
                j--;
                iteraciones++; // comparación y asignación
            }
            arreglo[j + 1] = key;
            iteraciones++; // asignación final
        }
        return iteraciones;
    }
}
