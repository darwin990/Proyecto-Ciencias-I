import random
import math

class Politico:
    def __init__(self, id, dineroRobado, edad, fechaNacimiento):
        self.id = id
        self.dineroRobado = dineroRobado
        self.edad = edad
        self.fechaNacimiento = fechaNacimiento

class Modelo:
    def __init__(self):
        self.arrayPoliticos = []
        self.matrizPoliticos = []
        self.k = 0
        self.m = 0

    def generar_array(self, n):
        self.arrayPoliticos.clear()
        for i in range(n):
            edad = random.randint(30, 79)
            p = Politico(i + 1, random.randint(100, 1000000), edad, str(2024 - edad))
            self.arrayPoliticos.append(p)

    def generar_matriz(self, n):
        self.matrizPoliticos.clear()
        self.k = math.ceil(math.sqrt(n))
        self.m = math.ceil(n / self.k)
        total = self.k * self.m
        politicos = []

        for i in range(n):
            edad = random.randint(30, 79)
            p = Politico(i + 1, random.randint(100, 1000000), edad, str(2024 - edad))
            politicos.append(p)

        while len(politicos) < total:
            p = Politico(-1, -1, -1, "X")
            politicos.append(p)

        index = 0
        for i in range(self.k):
            fila = []
            for j in range(self.m):
                fila.append(politicos[index])
                index += 1
            self.matrizPoliticos.append(fila)

    def ordenar_array(self, algoritmo):
        if algoritmo == 1:
            self.bubble_sort(self.arrayPoliticos, False)  # Ordenar por dinero robado
        else:
            self.selection_sort(self.arrayPoliticos, False)  # Ordenar por dinero robado

    def ordenar_matriz(self, algoritmo):
        for i in range(len(self.matrizPoliticos)):
            if algoritmo == 1:
                self.bubble_sort(self.matrizPoliticos[i], False)
            else:
                self.selection_sort(self.matrizPoliticos[i], False)

        for j in range(self.m):
            for i in range(self.k - 1):
                min_index = i
                for l in range(i + 1, self.k):
                    if self.matrizPoliticos[l][j].edad < self.matrizPoliticos[min_index][j].edad:
                        min_index = l
                self.matrizPoliticos[i][j], self.matrizPoliticos[min_index][j] = self.matrizPoliticos[min_index][j], self.matrizPoliticos[i][j]

    def bubble_sort(self, vec, ordenar_por_edad):
        for i in range(len(vec) - 1):
            for j in range(len(vec) - i - 1):
                if (ordenar_por_edad and vec[j].edad > vec[j + 1].edad) or \
                   (not ordenar_por_edad and vec[j].dineroRobado > vec[j + 1].dineroRobado):
                    vec[j], vec[j + 1] = vec[j + 1], vec[j]

    def selection_sort(self, vec, ordenar_por_edad):
        for i in range(len(vec) - 1):
            min_index = i
            for j in range(i + 1, len(vec)):
                if (ordenar_por_edad and vec[j].edad < vec[min_index].edad) or \
                   (not ordenar_por_edad and vec[j].dineroRobado < vec[min_index].dineroRobado):
                    min_index = j
            vec[i], vec[min_index] = vec[min_index], vec[i]
