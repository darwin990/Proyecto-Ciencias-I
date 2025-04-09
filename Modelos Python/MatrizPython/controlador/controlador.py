from modelo.politico import Politico
from modelo.ladron import Ladron

class Controlador:
    def __init__(self, algoritmos_ordenamiento):
        """
        algoritmos_ordenamiento: dict con nombre => instancia del algoritmo
        """
        self.algoritmos = algoritmos_ordenamiento
        
    def generar_y_ordenar_incrementalmente(self, n_max, delta, algoritmo_nombre):
        if algoritmo_nombre not in self.algoritmos:
            raise ValueError(f"Algoritmo '{algoritmo_nombre}' no está disponible.")

        algoritmo = self.algoritmos[algoritmo_nombre]

        import random
        historial = []

        # Comenzar con tamaño aleatorio entre 10 y n_max
        size = random.randint(10, n_max)
        politicos = Politico.generar_politicos(size)

        while size <= n_max:
            # Ordenar y registrar iteraciones
            politicos = algoritmo.sort(politicos, key=lambda p: p.dinero)
            historial.append({
                'tamaño': size,
                'iteraciones': algoritmo.iteraciones,
                'politicos': list(politicos)  # copia del arreglo actual
            })

            # Aumentar el tamaño
            size += delta
            if size > n_max:
                break
            nuevos = Politico.generar_politicos(delta)
            politicos.extend(nuevos)

        return historial

    def generar_politicos(self, cantidad):
        return Politico.generar_politicos(cantidad)

    def ordenar_politicos(self, politicos, algoritmo_nombre):
        if algoritmo_nombre not in self.algoritmos:
            raise ValueError(f"Algoritmo '{algoritmo_nombre}' no está disponible.")
        algoritmo = self.algoritmos[algoritmo_nombre]
        politicos_ordenados = algoritmo.sort(politicos, key=lambda p: p.dinero)
        return politicos_ordenados, algoritmo.iteraciones

    def generar_ladrones(self, total):
        import random
        ladrones = []
        for i in range(total):
            dinero = random.randint(100, 1_000_000)
            edad = random.randint(18, 80)
            ladrones.append(Ladron(id=i+1, dinero=dinero, edad=edad))
        return ladrones

    def organizar_ladrones_en_matriz(self, ladrones, k, m):
        matriz = []
        index = 0
        for i in range(k):
            fila = ladrones[index:index + m]
            fila.sort(key=lambda l: l.edad)
            matriz.append(fila)
            index += m
        return matriz

    def ordenar_matriz_por_dinero(self, matriz, algoritmo_nombre):
        if algoritmo_nombre not in self.algoritmos:
            raise ValueError(f"Algoritmo '{algoritmo_nombre}' no está disponible.")
        algoritmo = self.algoritmos[algoritmo_nombre]
        # Flatten la matriz para ordenar por dinero
        lista = [l for fila in matriz for l in fila]
        lista_ordenada = algoritmo.sort(lista, key=lambda l: l.dinero)

        # Reconstruir matriz con orden por dinero y luego ordenar por edad por fila
        k = len(matriz)
        m = len(matriz[0])
        nueva_matriz = []
        index = 0
        for i in range(k):
            fila = lista_ordenada[index:index + m]
            fila.sort(key=lambda l: l.edad)
            nueva_matriz.append(fila)
            index += m
        return nueva_matriz, algoritmo.iteraciones
