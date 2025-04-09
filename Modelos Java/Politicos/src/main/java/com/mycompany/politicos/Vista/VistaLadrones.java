/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.politicos.Vista;

/**
 *
 * @author moral
 */
import com.mycompany.politicos.Controlador.Controlador;
import com.mycompany.politicos.Modelo.Resultado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VistaLadrones extends JFrame {
    private JTextField txtFilas, txtColumnas;
    private JComboBox<String> cbAlgoritmos;
    private DefaultTableModel tableModel;
    private Controlador controlador;

    public VistaLadrones(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Simulador de Ordenamiento de Ladrones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Filas (k):"));
        txtFilas = new JTextField();
        add(txtFilas);

        add(new JLabel("Columnas (m):"));
        txtColumnas = new JTextField();
        add(txtColumnas);

        add(new JLabel("Algoritmo:"));
        cbAlgoritmos = new JComboBox<>(controlador.getNombresAlgoritmos());
        add(cbAlgoritmos);

        JButton btnOrdenar = new JButton("Ordenar Matriz");
        btnOrdenar.addActionListener(this::ordenarMatriz);
        add(btnOrdenar);

        JButton btnMostrar = new JButton("Mostrar Matrices");
        btnMostrar.addActionListener(this::mostrarMatrices);
        add(btnMostrar);

        tableModel = new DefaultTableModel(new Object[]{"Algoritmo", "Tiempo (ms)", "Iteraciones"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table));

        setSize(600, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ordenarMatriz(ActionEvent e) {
        try {
            int k = Integer.parseInt(txtFilas.getText());
            int m = Integer.parseInt(txtColumnas.getText());
            String algoritmo = (String) cbAlgoritmos.getSelectedItem();

            Resultado resultado = controlador.ordenarLadrones(k, m, algoritmo);
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{algoritmo, resultado.getTiempo(), resultado.getIteraciones()});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMatrices(ActionEvent e) {
        String original = controlador.matrizToString(controlador.getUltimaMatrizDesordenada());
        String ordenada = controlador.matrizToString(controlador.getUltimaMatrizOrdenada());

        JTextArea txt = new JTextArea("Desordenada:\n" + original + "\n\nOrdenada:\n" + ordenada);
        txt.setWrapStyleWord(true);
        txt.setLineWrap(true);
        txt.setEditable(false);
        JScrollPane scroll = new JScrollPane(txt);

        JOptionPane.showMessageDialog(this, scroll, "Matrices", JOptionPane.INFORMATION_MESSAGE);
    }
}

