import random

class Politico:
    def __init__(self, id, dinero_robado, fecha_nacimiento):
        self.id = id
        self.dinero_robado = dinero_robado
        self.fecha_nacimiento = fecha_nacimiento

class Modelo:
    def __init__(self):
        self.politicos = []

    def generar_politicos(self, n):
        random.seed()
        self.politicos = []
        for i in range(n):
            id = i + 1
            dinero_robado = random.randint(100, 1000000)
            fecha_nacimiento = self.generar_fecha_aleatoria()
            self.politicos.append(Politico(id, dinero_robado, fecha_nacimiento))

    def ordenar_politicos(self, metodo):
        if metodo == "bubble":
            self.bubble_sort()
        elif metodo == "selection":
            self.selection_sort()
        else:
            print("Metodo de ordenamiento invalido. Se usara Bubble Sort por defecto.")
            self.bubble_sort()

    def bubble_sort(self):
        n = len(self.politicos)
        for i in range(n - 1):
            for j in range(n - i - 1):
                if self.politicos[j].dinero_robado > self.politicos[j + 1].dinero_robado:
                    self.politicos[j], self.politicos[j + 1] = self.politicos[j + 1], self.politicos[j]

    def selection_sort(self):
        n = len(self.politicos)
        for i in range(n - 1):
            min_index = i
            for j in range(i + 1, n):
                if self.politicos[j].dinero_robado < self.politicos[min_index].dinero_robado:
                    min_index = j
            self.politicos[i], self.politicos[min_index] = self.politicos[min_index], self.politicos[i]

    def generar_fecha_aleatoria(self):
        dia = random.randint(1, 28)
        mes = random.randint(1, 12)
        anio = random.randint(1950, 1999)
        return f"{dia}/{mes}/{anio}"
