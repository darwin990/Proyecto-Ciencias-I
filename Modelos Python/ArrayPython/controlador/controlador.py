from modelo.modelo import Modelo
from vista.vista import Vista

class Controlador:
    def __init__(self):
        self.modelo = Modelo()
        self.vista = Vista()

    def ejecutar(self):
        try:
            n = int(input("Ingrese el numero de politicos (minimo 10): "))
        except ValueError:
            n = 10

        if n < 10:
            print("El numero minimo de politicos es 10. Se establecera n = 10 por defecto.")
            n = 10

        metodo = self.vista.pedir_metodo_ordenamiento()

        self.modelo.generar_politicos(n)
        self.vista.mostrar_politicos(self.modelo.politicos, "\nLista de politicos generada:")

        self.modelo.ordenar_politicos(metodo)
        self.vista.mostrar_politicos(self.modelo.politicos, "\nLista de politicos ordenada por dinero robado:")
