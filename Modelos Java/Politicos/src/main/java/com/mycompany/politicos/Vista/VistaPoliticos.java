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
import java.util.List;

public class VistaPoliticos extends JFrame {
    private JTextField txtTamano, txtDelta;
    private JComboBox<String> cbAlgoritmos;
    private DefaultTableModel tableModel;
    private Controlador controlador;

    public VistaPoliticos(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Simulador de Ordenamiento de Políticos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel("Tamaño Inicial:"));
        txtTamano = new JTextField();
        add(txtTamano);

        add(new JLabel("Delta (crecimiento):"));
        txtDelta = new JTextField();
        add(txtDelta);

        add(new JLabel("Algoritmo:"));
        cbAlgoritmos = new JComboBox<>(controlador.getNombresAlgoritmos());
        add(cbAlgoritmos);

        JButton btnEjecutar = new JButton("Ejecutar Ordenamiento");
        btnEjecutar.addActionListener(this::ejecutarOrdenamiento);
        add(btnEjecutar);

        JButton btnMostrar = new JButton("Mostrar Arreglos");
        btnMostrar.addActionListener(this::mostrarArreglos);
        add(btnMostrar);

        tableModel = new DefaultTableModel(new Object[]{"Algoritmo", "Caso", "Tiempo (ms)", "Iteraciones"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table));

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ejecutarOrdenamiento(ActionEvent e) {
        try {
            int n = Integer.parseInt(txtTamano.getText());
            String algoritmo = (String) cbAlgoritmos.getSelectedItem();
            tableModel.setRowCount(0);

            for (String caso : new String[]{"Ordenado", "Desordenado", "Orden Inverso"}) {
                Resultado resultado = controlador.ordenarPoliticos(n, algoritmo, caso);
                tableModel.addRow(new Object[]{algoritmo, caso, resultado.getTiempo(), resultado.getIteraciones()});
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarArreglos(ActionEvent e) {
        List<Integer> desordenado = controlador.getUltimoArrayDesordenado();
        List<Integer> ordenado = controlador.getUltimoArrayOrdenado();

        JTextArea txt = new JTextArea("Desordenado:\n" + desordenado + "\n\nOrdenado:\n" + ordenado);
        txt.setWrapStyleWord(true);
        txt.setLineWrap(true);
        txt.setEditable(false);
        JScrollPane scroll = new JScrollPane(txt);

        JOptionPane.showMessageDialog(this, scroll, "Arreglos", JOptionPane.INFORMATION_MESSAGE);
    }
}
