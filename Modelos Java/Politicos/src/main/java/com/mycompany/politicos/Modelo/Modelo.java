package com.mycompany.politicos.Modelo;

import java.util.*;

public class Modelo {
    public List<Politico> arrayPoliticos = new ArrayList<>();
    public List<List<Politico>> matrizPoliticos = new ArrayList<>();
    public int k, m;

    public void generarArray(int n) {
        arrayPoliticos.clear();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int edad = rand.nextInt(50) + 30;
            Politico p = new Politico(i + 1, rand.nextInt(1000000) + 100, edad, String.valueOf(2024 - edad));
            arrayPoliticos.add(p);
        }
    }

    public void generarMatriz(int n) {
        matrizPoliticos.clear();
        Random rand = new Random();
        k = (int) Math.ceil(Math.sqrt(n));
        m = (int) Math.ceil((double) n / k);
        int total = k * m;

        List<Politico> politicos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int edad = rand.nextInt(50) + 30;
            politicos.add(new Politico(i + 1, rand.nextInt(1000000) + 100, edad, String.valueOf(2024 - edad)));
        }

        while (politicos.size() < total) {
            politicos.add(new Politico());
        }

        int index = 0;
        for (int i = 0; i < k; i++) {
            List<Politico> fila = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                fila.add(politicos.get(index++));
            }
            matrizPoliticos.add(fila);
        }
    }

    public void ordenarArray(int algoritmo) {
        switch (algoritmo) {
            case 1 -> bubbleSort(arrayPoliticos, false);      // Por dinero robado
            case 2 -> selectionSort(arrayPoliticos, false);
            case 3 -> insertionSort(arrayPoliticos, false);
            case 4 -> quickSort(arrayPoliticos, 0, arrayPoliticos.size() - 1, false);
        }
    }

    public void ordenarMatriz(int algoritmo) {
        for (List<Politico> fila : matrizPoliticos) {
            if (algoritmo == 1)
                bubbleSort(fila, true); // Ordenar por edad
            else if (algoritmo == 2)
                selectionSort(fila, true);
        }

        // Ordenar por columnas (por edad)
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

    private void bubbleSort(List<Politico> lista, boolean ordenarPorEdad) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Politico a = lista.get(j);
                Politico b = lista.get(j + 1);
                if ((ordenarPorEdad && a.edad > b.edad) || (!ordenarPorEdad && a.dineroRobado > b.dineroRobado)) {
                    lista.set(j, b);
                    lista.set(j + 1, a);
                }
            }
        }
    }

    private void selectionSort(List<Politico> lista, boolean ordenarPorEdad) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                Politico a = lista.get(j);
                Politico b = lista.get(minIdx);
                if ((ordenarPorEdad && a.edad < b.edad) || (!ordenarPorEdad && a.dineroRobado < b.dineroRobado)) {
                    minIdx = j;
                }
            }
            Collections.swap(lista, i, minIdx);
        }
    }

    private void insertionSort(List<Politico> lista, boolean ordenarPorEdad) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            Politico aux = lista.get(i);
            int j = i - 1;

            while (j >= 0 && ((ordenarPorEdad && lista.get(j).edad > aux.edad) || (!ordenarPorEdad && lista.get(j).dineroRobado > aux.dineroRobado))) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, aux);
        }
    }

    private void quickSort(List<Politico> lista, int low, int high, boolean ordenarPorEdad) {
        if (low < high) {
            int pi = partition(lista, low, high, ordenarPorEdad);
            quickSort(lista, low, pi - 1, ordenarPorEdad);
            quickSort(lista, pi + 1, high, ordenarPorEdad);
        }
    }

    private int partition(List<Politico> lista, int low, int high, boolean ordenarPorEdad) {
        Politico pivot = lista.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condicion = ordenarPorEdad
                    ? lista.get(j).edad < pivot.edad
                    : lista.get(j).dineroRobado < pivot.dineroRobado;

            if (condicion) {
                i++;
                Collections.swap(lista, i, j);
            }
        }

        Collections.swap(lista, i + 1, high);
        return i + 1;
    }
}
