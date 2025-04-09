import tkinter as tk
from tkinter import ttk, messagebox
import time

from modelo.bubble_sort import BubbleSort
from modelo.selection_sort import SelectionSort
from modelo.politico import Politico
from modelo.merge_sort import MergeSort
from modelo.insertion_sort import InsertionSort
from modelo.quicksort import QuickSort
from controlador.controlador import Controlador

class VistaOrdenamiento:
      def __init__(self, root):
          self.root = root
          self.root.title("Simulador de Ordenamiento de Políticos")
          
          self.algoritmos = {
    "MergeSort": MergeSort(),
    "BubbleSort": BubbleSort(),
    "SelectionSort": SelectionSort(),
    "InsertionSort": InsertionSort(),
    "QuickSort": QuickSort()
          }
  
          # Inputs
          tk.Label(root, text="Tamaño Inicial:").grid(row=0, column=0)
          self.input_tamano = tk.Entry(root)
          self.input_tamano.grid(row=0, column=1)
  
          tk.Label(root, text="Delta (crecimiento):").grid(row=1, column=0)
          self.input_delta = tk.Entry(root)
          self.input_delta.grid(row=1, column=1)
  
          # Selector de algoritmo
          tk.Label(root, text="Algoritmo:").grid(row=2, column=0)
          self.algoritmo_seleccionado = tk.StringVar()
          self.algoritmo_dropdown = ttk.Combobox(root, textvariable=self.algoritmo_seleccionado)
          self.algoritmo_dropdown['values'] = list(self.algoritmos.keys())
          self.algoritmo_dropdown.current(0)
          self.algoritmo_dropdown.grid(row=2, column=1)
  
          # Botón para ejecutar ordenamiento
          self.boton_generar = tk.Button(root, text="Ejecutar Ordenamiento", command=self.ejecutar)
          self.boton_generar.grid(row=3, column=0, columnspan=2, pady=10)
  
          # Botón para mostrar arrays desordenado y ordenado
          self.boton_mostrar_arrays = tk.Button(root, text="Mostrar Arreglos", command=self.mostrar_arrays)
          self.boton_mostrar_arrays.grid(row=4, column=0, columnspan=2, pady=5)
  
          # Panel de resultados
          self.tree = ttk.Treeview(root, columns=("algoritmo", "caso", "tiempo", "iteraciones"), show="headings")
          self.tree.heading("algoritmo", text="Algoritmo")
          self.tree.heading("caso", text="Caso")
          self.tree.heading("tiempo", text="Tiempo (s)")
          self.tree.heading("iteraciones", text="Iteraciones")
          self.tree.grid(row=5, column=0, columnspan=2, sticky="nsew")
  
          root.grid_rowconfigure(5, weight=1)
          root.grid_columnconfigure(1, weight=1)
  
          # Algoritmos disponibles
          
          self.controlador = Controlador(self.algoritmos)
  
          # Variables para mostrar arrays
          self.ultimo_array_desordenado = []
          self.ultimo_array_ordenado = []
  
      def ejecutar(self):
          try:
              n = int(self.input_tamano.get())
              delta = int(self.input_delta.get())
          except ValueError:
              messagebox.showerror("Error", "Por favor ingresa números enteros válidos.")
              return
  
          algoritmo_nombre = self.algoritmo_seleccionado.get()
          if algoritmo_nombre not in self.algoritmos:
              messagebox.showerror("Error", f"Algoritmo '{algoritmo_nombre}' no disponible.")
              return
  
          self.tree.delete(*self.tree.get_children())
  
          for caso in ["Ordenado", "Desordenado", "Orden Inverso"]:
              self.algoritmos[algoritmo_nombre] = self.algoritmos[algoritmo_nombre].__class__()
              controlador = Controlador({algoritmo_nombre: self.algoritmos[algoritmo_nombre]})
  
              politicos = Politico.generar_politicos(n)
              if caso == "Ordenado":
                  politicos.sort(key=lambda p: p.dinero)
              elif caso == "Orden Inverso":
                  politicos.sort(key=lambda p: p.dinero, reverse=True)
  
              self.ultimo_array_desordenado = [p.dinero for p in politicos]
              inicio = time.time()
              politicos = controlador.algoritmos[algoritmo_nombre].sort(politicos, key=lambda p: p.dinero)
              fin = time.time()
              self.ultimo_array_ordenado = [p.dinero for p in politicos]
  
              self.tree.insert("", "end", values=(
                  algoritmo_nombre,
                  caso,
                  format(fin - inicio, ".10f"),
                  controlador.algoritmos[algoritmo_nombre].iteraciones
              ))
  
      def mostrar_arrays(self):
          ventana = tk.Toplevel(self.root)
          ventana.title("Arreglos Desordenado vs Ordenado")
  
          tk.Label(ventana, text="Arreglo Desordenado:").pack()
          texto1 = tk.Text(ventana, height=10, wrap="word")
          texto1.insert("1.0", str(self.ultimo_array_desordenado))
          texto1.pack(fill="both", expand=True)
  
          tk.Label(ventana, text="Arreglo Ordenado:").pack()
          texto2 = tk.Text(ventana, height=10, wrap="word")
          texto2.insert("1.0", str(self.ultimo_array_ordenado))
          texto2.pack(fill="both", expand=True)
          
if __name__ == '__main__':
    root = tk.Tk()
    app = VistaOrdenamiento(root)
    root.mainloop()
