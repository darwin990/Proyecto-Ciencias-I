import random
import math
from datetime import datetime

class Politico:
    def __init__(self, id, dinero_robado, fecha_nacimiento, edad):
        self.id = id
        self.dinero_robado = dinero_robado
        self.fecha_nacimiento = fecha_nacimiento
        self.edad = edad

class Modelo:
    def __init__(self):
        self.matriz_politicos = []
        self.k = 0
        self.m = 0

    def generar_politicos(self, n):
        self.k = math.ceil(math.sqrt(n))
        self.m = math.ceil(n / self.k)
        total = self.k * self.m
        politicos = []

        for i in range(n):
            edad = random.randint(30, 79)
            anio_nacimiento = datetime.now().year - edad
            p = Politico(
                id=i + 1,
                dinero_robado=random.randint(100, 999999),
                fecha_nacimiento=str(anio_nacimiento),
                edad=edad
            )
            politicos.append(p)

        while len(politicos) < total:
            politicos.append(Politico(-1, -1, "X", -1))

        self.matriz_politicos = []
        index = 0
        for i in range(self.k):
            fila = []
            for j in range(self.m):
                fila.append(politicos[index])
                index += 1
            self.matriz_politicos.append(fila)

    def ordenar_por_edad(self, usar_bubble_sort):
        for fila in self.matriz_politicos:
            if usar_bubble_sort:
                self.bubble_sort(fila, por_edad=True)
            else:
                self.selection_sort(fila, por_edad=True)

    def ordenar_por_dinero_y_edad(self, usar_bubble_sort):
        for fila in self.matriz_politicos:
            if usar_bubble_sort:
                self.bubble_sort(fila, por_edad=False)
            else:
                self.selection_sort(fila, por_edad=False)

        for j in range(self.m):
            for i in range(self.k - 1):
                min_index = i
                for l in range(i + 1, self.k):
                    if self.matriz_politicos[l][j].edad < self.matriz_politicos[min_index][j].edad:
                        min_index = l
                self.matriz_politicos[i][j], self.matriz_politicos[min_index][j] =                     self.matriz_politicos[min_index][j], self.matriz_politicos[i][j]

    def bubble_sort(self, fila, por_edad):
        for i in range(len(fila) - 1):
            for j in range(len(fila) - i - 1):
                if ((por_edad and fila[j].edad > fila[j + 1].edad) or
                    (not por_edad and fila[j].dinero_robado > fila[j + 1].dinero_robado)):
                    fila[j], fila[j + 1] = fila[j + 1], fila[j]

    def selection_sort(self, fila, por_edad):
        for i in range(len(fila) - 1):
            min_index = i
            for j in range(i + 1, len(fila)):
                if ((por_edad and fila[j].edad < fila[min_index].edad) or
                    (not por_edad and fila[j].dinero_robado < fila[min_index].dinero_robado)):
                    min_index = j
            fila[i], fila[min_index] = fila[min_index], fila[i]
