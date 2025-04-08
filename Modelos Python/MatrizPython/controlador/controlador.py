from modelo.modelo import Modelo
from vista.vista import Vista

class Controlador:
    def __init__(self):
        self.modelo = Modelo()
        self.vista = Vista()

    def ejecutar(self):
        try:
            n = int(input("Ingrese el número de políticos (mínimo 10): "))
        except ValueError:
            n = 10

        if n < 10:
            print("Se establecerá n = 10 por defecto.")
            n = 10

        try:
            opcion = int(input("Seleccione algoritmo de ordenamiento:\n1. Bubble Sort\n2. Selection Sort\nOpción: "))
        except ValueError:
            opcion = 1

        usar_bubble_sort = (opcion == 1)

        self.modelo.generar_politicos(n)
        self.vista.mostrar_matriz(self.modelo.matriz_politicos, "Matriz generada:")

        self.modelo.ordenar_por_edad(usar_bubble_sort)
        self.vista.mostrar_matriz(self.modelo.matriz_politicos, "Filas ordenadas por edad:")

        self.modelo.ordenar_por_dinero_y_edad(usar_bubble_sort)
        self.vista.mostrar_matriz(self.modelo.matriz_politicos, "Filas ordenadas por dinero y columnas por edad:")
