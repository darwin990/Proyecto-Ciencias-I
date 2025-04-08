class Vista:
    def mostrar_matriz(self, matriz, mensaje):
        print(f"\n{mensaje}")
        for fila in matriz:
            for p in fila:
                if p.id == -1:
                    print("[ X ]", end=" ")
                else:
                    print(f"[{p.id}|${p.dinero_robado}|{p.edad}]", end=" ")
            print()
        print("-" * 30)
