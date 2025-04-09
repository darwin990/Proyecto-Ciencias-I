/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.politicos;

/**
 *
 * @author 57320
 */
import com.mycompany.politicos.Controlador.Controlador;
import com.mycompany.politicos.Modelo.Politico;
import com.mycompany.politicos.Vista.VistaPoliticos;

import com.mycompany.politicos.Controlador.Controlador;
import com.mycompany.politicos.Vista.VistaLadrones;
import com.mycompany.politicos.Vista.VistaPoliticos;

public class Main {
    public static void main(String[] args) {
        // Asegurar que se ejecuta en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            Controlador controlador = new Controlador();
            new VistaPoliticos(controlador);
            new VistaLadrones(controlador);
        });
    }
}
