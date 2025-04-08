from modelo.modelo import Modelo
from vista.vista import Vista

class Controlador:
    def __init__(self):
        self.modelo = Modelo()
        self.vista = Vista()

    def ejecutar(self):
        try:
            n = int(input("Ingrese el numero de politicos (minimo 10): "))
        except:
            n = 10
        if n < 10:
            print("Se asignara n = 10 por defecto.")
            n = 10

        estructura = int(input("Seleccione la estructura de datos:\n1. Array\n2. Matriz\nOpcion: "))
        algoritmo = int(input("Seleccione el algoritmo de ordenamiento:\n1. Bubble Sort\n2. Selection Sort\nOpcion: "))
        if algoritmo not in [1, 2]:
            print("Opcion invalida. Se usara Bubble Sort por defecto.")
            algoritmo = 1

        if estructura == 1:
            self.modelo.generar_array(n)
            self.vista.mostrar_array(self.modelo.arrayPoliticos, "\nArray de politicos generado:")
            self.modelo.ordenar_array(algoritmo)
            self.vista.mostrar_array(self.modelo.arrayPoliticos, "\nArray ordenado por dinero robado:")
        else:
            self.modelo.generar_matriz(n)
            self.vista.mostrar_matriz(self.modelo.matrizPoliticos, "\nMatriz de politicos generada:")
            self.modelo.ordenar_matriz(algoritmo)
            self.vista.mostrar_matriz(self.modelo.matrizPoliticos, "\nMatriz ordenada con Filas por dinero robado y columnas por edad:")
