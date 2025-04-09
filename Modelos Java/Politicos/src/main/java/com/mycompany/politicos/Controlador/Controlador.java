/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Controlador;

/**
 *
 * @author 57320
 */

import java.util.*;

import com.mycompany.politicos.Modelo.AlgoritmoOrdenamiento;
import com.mycompany.politicos.Modelo.Ladron;
import com.mycompany.politicos.Modelo.Politico;
import com.mycompany.politicos.Modelo.BubbleSort;
import com.mycompany.politicos.Modelo.InsertionSort;
import com.mycompany.politicos.Modelo.MergeSort;
import com.mycompany.politicos.Modelo.QuickSort;
import com.mycompany.politicos.Modelo.Resultado;
import com.mycompany.politicos.Modelo.SelectionSort;
import java.time.LocalDate;

public class Controlador {
    private Random random = new Random();
    private int contadorID = 1;  
    private Ladron[][] ultimaMatrizOriginal;
    private Ladron[][] ultimaMatrizOrdenada;
    private final Map<String, AlgoritmoOrdenamiento<Ladron>> algoritmosLadrones = new HashMap<>();
    private final Map<String, AlgoritmoOrdenamiento<Politico>> algoritmosPoliticos = new HashMap<>();
    private Politico[] ultimoArrayOriginal;
    private Politico[] ultimoArrayOrdenado;
    

public Controlador() {
    // Aquí registras tus algoritmos
    
    // Ladrones
    algoritmosLadrones.put("BubbleSort", new BubbleSort<>());
    algoritmosLadrones.put("MergeSort", new MergeSort<>());
    algoritmosLadrones.put("SelectionSort", new SelectionSort<>());
    algoritmosLadrones.put("QuickSort", new QuickSort<>());
    algoritmosLadrones.put("InsertionSort", new InsertionSort<>());

    // Políticos
    algoritmosPoliticos.put("BubbleSort", new BubbleSort<>());
    algoritmosPoliticos.put("MergeSort", new MergeSort<>());
    algoritmosPoliticos.put("SelectionSort", new SelectionSort<>());
    algoritmosPoliticos.put("QuickSort", new QuickSort<>());
    algoritmosPoliticos.put("InsertionSort", new InsertionSort<>());
}



public Resultado ordenarPoliticos(int n, String nombreAlgoritmo, String tipoOrden) {
    Politico[] politicos = generarPoliticos(n);
    ultimoArrayOriginal = Arrays.copyOf(politicos, politicos.length);

    // Orden según tipo
    Comparator<Politico> comparador = Comparator.comparingInt(Politico::getDineroRobado);
    if (tipoOrden.equals("Ordenado")) {
        Arrays.sort(politicos, comparador);
    } else if (tipoOrden.equals("Orden Inverso")) {
        Arrays.sort(politicos, comparador.reversed());
    } else if (tipoOrden.equals("Desordenado")) {
        // lo dejamos como está
    }

    AlgoritmoOrdenamiento<Politico> algoritmo = (AlgoritmoOrdenamiento<Politico>) algoritmosPoliticos.get(nombreAlgoritmo);

    long inicio = System.currentTimeMillis();
    int iteraciones = algoritmo.ordenar(politicos, comparador);
    long fin = System.currentTimeMillis();

    ultimoArrayOrdenado = Arrays.copyOf(politicos, politicos.length);
    return new Resultado(fin - inicio, iteraciones);
}

public Resultado ordenarLadrones(int filas, int columnas, String nombreAlgoritmo) {
    Ladron[][] matriz = generarLadrones(filas, columnas);
    ultimaMatrizOriginal = clonarMatriz(matriz);

    AlgoritmoOrdenamiento<Ladron> algoritmo = algoritmosLadrones.get(nombreAlgoritmo);

    long inicio = System.currentTimeMillis();
    int iteraciones = ordenarMatrizLadrones(matriz, algoritmo);
    long fin = System.currentTimeMillis();

    ultimaMatrizOrdenada = clonarMatriz(matriz);
    return new Resultado(fin - inicio, iteraciones);
}


    // Generar lista de políticos aleatorios
    public Politico[] generarPoliticos(int cantidad) {
        Politico[] politicos = new Politico[cantidad];
        for (int i = 0; i < cantidad; i++) {
            int dinero = 100 + random.nextInt(1_000_000 - 100);
            LocalDate fechaNacimiento = generarFechaAleatoria();
            politicos[i] = new Politico(contadorID++, dinero, fechaNacimiento);
        }
        return politicos;
    }

    // Generar matriz de ladrones aleatorios
    public Ladron[][] generarLadrones(int filas, int columnas) {
        Ladron[][] matriz = new Ladron[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int dinero = 100 + random.nextInt(1_000_000 - 100);
                LocalDate fechaNacimiento = generarFechaAleatoria();
                matriz[i][j] = new Ladron(contadorID++, dinero, fechaNacimiento);
            }
        }
        return matriz;
    }

    // Clonar matriz (para preservar la original)
    public Ladron[][] clonarMatriz(Ladron[][] original) {
        Ladron[][] copia = new Ladron[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copia;
    }

    // Ordenar matriz por dinero y luego por edad
    public int ordenarMatrizPorDinero(Ladron[][] matriz, AlgoritmoOrdenamiento<Ladron> algoritmo) {
        return ordenarMatrizLadrones(matriz, algoritmo);
    }

    // Organizar ladrones en matriz (alternativa por si los recibes en array plano)
    public Ladron[][] organizarLadronesEnMatriz(Ladron[] ladrones, int filas, int columnas) {
        Ladron[][] matriz = new Ladron[filas][columnas];
        for (int i = 0; i < ladrones.length; i++) {
            int fila = i / columnas;
            int col = i % columnas;
            matriz[fila][col] = ladrones[i];
        }
        return matriz;
    }

    // Ejecutar ordenamiento de políticos (para una vista general)
    public int ordenarPoliticos(Politico[] politicos, AlgoritmoOrdenamiento<Politico> algoritmo) {
        return algoritmo.ordenar(politicos, Comparator.comparingInt(Politico::getDineroRobado));
    }

    private LocalDate generarFechaAleatoria() {
        int año = 1950 + random.nextInt(40); // entre 1950 y 1990
        int mes = 1 + random.nextInt(12);
        int dia = 1 + random.nextInt(28); // simplificado
        return LocalDate.of(año, mes, dia);
    }
    
    public <T> int ordenarMatrizLadrones(Ladron[][] matriz, AlgoritmoOrdenamiento<Ladron> algoritmo) {
    int totalIteraciones = 0;

    // Ordenar filas por dinero robado
    for (int i = 0; i < matriz.length; i++) {
        totalIteraciones += algoritmo.ordenar(matriz[i], Comparator.comparingInt(Ladron::getDineroRobado));
    }

    // Ordenar columnas por edad
    for (int j = 0; j < matriz[0].length; j++) {
        Ladron[] columna = new Ladron[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            columna[i] = matriz[i][j];
        }

        totalIteraciones += algoritmo.ordenar(columna, Comparator.comparing(Ladron::getFechaNacimiento));

        for (int i = 0; i < matriz.length; i++) {
            matriz[i][j] = columna[i];
        }
    }

    return totalIteraciones;
}


    // Extraer valores de dinero de una lista de políticos
    public List<Integer> extraerValores(Politico[] politicos) {
        List<Integer> valores = new ArrayList<>();
        for (Politico p : politicos) {
            valores.add(p.getDineroRobado());
        }
        return valores;
    }
    
    public String matrizToString(Ladron[][] matriz) {
        if (matriz == null) return "Sin datos.";
        StringBuilder sb = new StringBuilder();
        for (Ladron[] fila : matriz) {
            for (Ladron l : fila) {
                sb.append(String.format("[%d | %s]", l.getDineroRobado(), l.getFechaNacimiento())).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public String[] getNombresAlgoritmos() {
        return algoritmosPoliticos.keySet().toArray(new String[0]);
    }

    
    public Ladron[][] getUltimaMatrizDesordenada() {
        return ultimaMatrizOriginal;
    }

    public Ladron[][] getUltimaMatrizOrdenada() {
        return ultimaMatrizOrdenada;
    }
    
    public List<Integer> getUltimoArrayDesordenado() {
        return extraerValores(ultimoArrayOriginal);
    }

    public List<Integer> getUltimoArrayOrdenado() {
        return extraerValores(ultimoArrayOrdenado);
    }



}
