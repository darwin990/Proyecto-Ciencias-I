import random
from datetime import datetime, timedelta

class Politico:
    def __init__(self, id, dinero, fecha_nacimiento):
        self.id = id
        self.dinero = dinero
        self.fecha_nacimiento = fecha_nacimiento

    def __repr__(self):
        return f"Politico(ID={self.id}, Dinero={self.dinero}, Nac={self.fecha_nacimiento.strftime('%Y-%m-%d')})"

    @staticmethod
    def generar_politicos(n):
        politicos = []
        for i in range(n):
            dinero = random.randint(100, 1_000_000)
            nacimiento = datetime.today() - timedelta(days=random.randint(18*365, 80*365))
            politicos.append(Politico(id=i+1, dinero=dinero, fecha_nacimiento=nacimiento))
        return politicos

