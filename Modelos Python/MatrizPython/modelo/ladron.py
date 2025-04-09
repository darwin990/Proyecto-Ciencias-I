import random

class Ladron:
    def __init__(self, id: int, dinero: int, edad: int):
        self.id = id
        self.dinero = dinero
        self.edad = edad

    def __repr__(self):
        return f"Ladron(ID={self.id}, Dinero={self.dinero}, Edad={self.edad})"

    def __str__(self):
        return f"ID:{self.id} | ${self.dinero}M | {self.edad} años"

    def __lt__(self, other):
        return self.dinero < other.dinero

    def as_tuple(self):
        return (self.id, self.dinero, self.edad)

    @staticmethod
    def generar_ladrones(n):
        ladrones = []
        for i in range(n):
            dinero = random.randint(100, 1_000_000)
            edad = random.randint(18, 80)
            ladrones.append(Ladron(id=i + 1, dinero=dinero, edad=edad))
        return ladrones

