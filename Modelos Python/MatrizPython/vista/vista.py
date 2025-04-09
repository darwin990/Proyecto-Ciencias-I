import tkinter as tk
from tkinter import ttk, messagebox
import time

from modelo.bubble_sort import BubbleSort
from modelo.selection_sort import SelectionSort
from modelo.merge_sort import MergeSort
from modelo.insertion import InsertionSort
from modelo.quicksort import QuickSort
from controlador.controlador import Controlador


class VistaLadronesOrdenamiento:
    def __init__(self, root):
        self.root = root
        self.root.title("Simulador de Ordenamiento de Ladrones en Matriz")

        self.algoritmos = {    
        "MergeSort": MergeSort(),
        "BubbleSort": BubbleSort(),
        "SelectionSort": SelectionSort(),
        "InsertionSort": InsertionSort(),
        "QuickSort": QuickSort()
        }

        self.controlador = Controlador(self.algoritmos)

        # --- Inputs ---
        tk.Label(root, text="Filas (k):").grid(row=0, column=0, sticky="w")
        self.input_filas = tk.Entry(root)
        self.input_filas.grid(row=0, column=1)

        tk.Label(root, text="Columnas (m):").grid(row=1, column=0, sticky="w")
        self.input_columnas = tk.Entry(root)
        self.input_columnas.grid(row=1, column=1)

        tk.Label(root, text="Algoritmo:").grid(row=2, column=0, sticky="w")
        self.algoritmo_seleccionado = tk.StringVar()
        self.algoritmo_dropdown = ttk.Combobox(root, textvariable=self.algoritmo_seleccionado)
        self.algoritmo_dropdown['values'] = list(self.algoritmos.keys())
        self.algoritmo_dropdown.current(0)
        self.algoritmo_dropdown.grid(row=2, column=1)

        # --- Botones ---
        self.boton_ordenar = tk.Button(root, text="Ordenar Matriz", command=self.ordenar_matriz)
        self.boton_ordenar.grid(row=3, column=0, columnspan=2, pady=10)

        self.boton_mostrar_matrices = tk.Button(root, text="Mostrar Matrices", command=self.mostrar_matrices)
        self.boton_mostrar_matrices.grid(row=4, column=0, columnspan=2, pady=5)

        # --- Tabla resultados ---
        self.tree = ttk.Treeview(root, columns=("algoritmo", "tiempo", "iteraciones"), show="headings")
        self.tree.heading("algoritmo", text="Algoritmo")
        self.tree.heading("tiempo", text="Tiempo (s)")
        self.tree.heading("iteraciones", text="Iteraciones")
        self.tree.grid(row=5, column=0, columnspan=2, sticky="nsew")

        root.grid_rowconfigure(5, weight=1)
        root.grid_columnconfigure(1, weight=1)

        # Guardar matrices para comparación
        self.matriz_desordenada = []
        self.matriz_ordenada = []

    def ordenar_matriz(self):
        try:
            k = int(self.input_filas.get())
            m = int(self.input_columnas.get())
        except ValueError:
            messagebox.showerror("Error", "Por favor ingresa números enteros válidos.")
            return

        total = k * m
        algoritmo_nombre = self.algoritmo_seleccionado.get()

        ladrones = self.controlador.generar_ladrones(total)
        matriz = self.controlador.organizar_ladrones_en_matriz(ladrones, k, m)
        self.matriz_desordenada = [fila.copy() for fila in matriz]

        # Ordenar matriz
        inicio = time.time()
        matriz_ordenada, iteraciones = self.controlador.ordenar_matriz_por_dinero(matriz, algoritmo_nombre)
        fin = time.time()

        self.matriz_ordenada = matriz_ordenada

        # Mostrar resultados
        self.tree.delete(*self.tree.get_children())
        self.tree.insert("", "end", values=(
            algoritmo_nombre,
            format(fin - inicio, ".10f"),
            iteraciones
        ))

    def mostrar_matrices(self):
        ventana = tk.Toplevel(self.root)
        ventana.title("Matrices Desordenada vs Ordenada")

        # Desordenada
        tk.Label(ventana, text="Matriz Desordenada:").pack()
        texto1 = tk.Text(ventana, height=10, wrap="word")
        texto1.insert("1.0", self.matriz_a_string(self.matriz_desordenada))
        texto1.pack(fill="both", expand=True)

        # Ordenada
        tk.Label(ventana, text="Matriz Ordenada:").pack()
        texto2 = tk.Text(ventana, height=10, wrap="word")
        texto2.insert("1.0", self.matriz_a_string(self.matriz_ordenada))
        texto2.pack(fill="both", expand=True)

    def matriz_a_string(self, matriz):
        return "\n".join(" | ".join(str(l) for l in fila) for fila in matriz)


if __name__ == '__main__':
    root = tk.Tk()
    app = VistaLadronesOrdenamiento(root)
    root.mainloop()
