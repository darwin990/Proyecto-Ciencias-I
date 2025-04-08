/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arrayjava.Vista;

/**
 *
 * @author 57320
 */
import com.mycompany.arrayjava.Modelo.Politico;
import java.util.ArrayList;
import java.util.Scanner;

public class Vista {

    public void mostrarPoliticos(ArrayList<Politico> politicos, String mensaje) {
        System.out.println(mensaje);
        System.out.printf("%-5s %-15s %s%n", "ID", "Dinero Robado", "Fecha de Nacimiento");
        System.out.println("---------------------------------------");
        for (Politico p : politicos) {
            System.out.printf("%-5d %-15d %s%n", p.id, p.dineroRobado, p.fechaNacimiento);
        }
        System.out.println("---------------------------------------");
    }

    public String pedirMetodoOrdenamiento() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Seleccione el algoritmo de ordenamiento (bubble / selection): ");
        return sc.nextLine();
    }
}
