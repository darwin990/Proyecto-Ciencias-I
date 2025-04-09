/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Modelo;

import java.util.Comparator;

/**
 *
 * @author moral
 * @param <T>
 */
public interface AlgoritmoOrdenamiento<T> {
    int ordenar(T[] arreglo, Comparator<T> comparador);
}
