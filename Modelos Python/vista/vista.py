class Vista:
    def mostrar_array(self, vec, mensaje):
        print(mensaje)
        print(f"{'ID':<5}{'Dinero Robado':<15}{'Edad':<10}Fecha")
        print("-" * 45)
        for p in vec:
            print(f"{p.id:<5}{p.dineroRobado:<15}{p.edad:<10}{p.fechaNacimiento}")
        print("-" * 45)

    def mostrar_matriz(self, matriz, mensaje):
        print(mensaje)
        for fila in matriz:
            for p in fila:
                if p.id == -1:
                    print("[ X ]", end=" ")
                else:
                    print(f"[{p.id}|${p.dineroRobado}|{p.edad}]", end=" ")
            print()
        print("-" * 30)