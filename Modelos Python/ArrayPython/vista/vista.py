class Vista:
    def mostrar_politicos(self, politicos, mensaje):
        print(mensaje)
        print(f"{'ID':<5}{'Dinero Robado':<15}Fecha de Nacimiento")
        print("-" * 40)
        for p in politicos:
            print(f"{p.id:<5}{p.dinero_robado:<15}{p.fecha_nacimiento}")
        print("-" * 40)

    def pedir_metodo_ordenamiento(self):
        metodo = input("Seleccione el algoritmo de ordenamiento (bubble / selection): ")
        return metodo
