#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <sstream>
#include <cmath>
#include <string>

using namespace std;

// =======================
// Modelo
// =======================

struct Politico {
    int id;
    int dineroRobado;
    string fechaNacimiento;
    int edad;
};

class Modelo {
public:
    vector< vector<Politico> > matrizPoliticos;
    int k, m;

    void generarPoliticos(int n) {
        srand(time(0));
        k = ceil(sqrt(n));
        m = ceil((double)n / k);
        int total = k * m;
        vector<Politico> politicos;

        for (int i = 0; i < n; ++i) {
            Politico p;
            p.id = i + 1;
            p.dineroRobado = rand() % 1000000 + 100;
            p.edad = rand() % 50 + 30;
            p.fechaNacimiento = intToString(2024 - p.edad);
            politicos.push_back(p);
        }

        while ((int)politicos.size() < total) {
            Politico p;
            p.id = -1;
            p.dineroRobado = -1;
            p.edad = -1;
            p.fechaNacimiento = "X";
            politicos.push_back(p);
        }

        int index = 0;
        for (int i = 0; i < k; i++) {
            vector<Politico> fila;
            for (int j = 0; j < m; j++) {
                fila.push_back(politicos[index++]);
            }
            matrizPoliticos.push_back(fila);
        }
    }

    void ordenarPorEdad(int algoritmo) {
        for (int i = 0; i < (int)matrizPoliticos.size(); ++i) {
            if (algoritmo == 1)
                bubbleSort(matrizPoliticos[i], true);
            else
                selectionSort(matrizPoliticos[i], true);
        }
    }

    void ordenarPorDineroYEdad(int algoritmo) {
        for (int i = 0; i < (int)matrizPoliticos.size(); ++i) {
            if (algoritmo == 1)
                bubbleSort(matrizPoliticos[i], false);
            else
                selectionSort(matrizPoliticos[i], false);
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < k - 1; i++) {
                int minIndex = i;
                for (int l = i + 1; l < k; l++) {
                    if (matrizPoliticos[l][j].edad < matrizPoliticos[minIndex][j].edad) {
                        minIndex = l;
                    }
                }
                Politico temp = matrizPoliticos[i][j];
                matrizPoliticos[i][j] = matrizPoliticos[minIndex][j];
                matrizPoliticos[minIndex][j] = temp;
            }
        }
    }

private:
    string intToString(int num) {
        stringstream ss;
        ss << num;
        return ss.str();
    }

    void bubbleSort(vector<Politico>& fila, bool ordenarPorEdad) {
        for (int i = 0; i < (int)fila.size() - 1; ++i) {
            for (int j = 0; j < (int)fila.size() - i - 1; ++j) {
                if ((ordenarPorEdad && fila[j].edad > fila[j + 1].edad) ||
                    (!ordenarPorEdad && fila[j].dineroRobado > fila[j + 1].dineroRobado)) {
                    Politico temp = fila[j];
                    fila[j] = fila[j + 1];
                    fila[j + 1] = temp;
                }
            }
        }
    }

    void selectionSort(vector<Politico>& fila, bool ordenarPorEdad) {
        for (int i = 0; i < (int)fila.size() - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < (int)fila.size(); ++j) {
                if ((ordenarPorEdad && fila[j].edad < fila[minIndex].edad) ||
                    (!ordenarPorEdad && fila[j].dineroRobado < fila[minIndex].dineroRobado)) {
                    minIndex = j;
                }
            }
            Politico temp = fila[i];
            fila[i] = fila[minIndex];
            fila[minIndex] = temp;
        }
    }
};

// =======================
// Vista
// =======================

class Vista {
public:
    void mostrarMatriz(vector< vector<Politico> > matriz, string mensaje) {
        cout << mensaje << endl;
        for (int i = 0; i < (int)matriz.size(); ++i) {
            for (int j = 0; j < (int)matriz[i].size(); ++j) {
                Politico p = matriz[i][j];
                if (p.id == -1) {
                    cout << "[ X ] ";
                } else {
                    cout << "[" << p.id << "|$" << p.dineroRobado << "|" << p.edad << "] ";
                }
            }
            cout << endl;
        }
        cout << "-----------------------------" << endl;
    }
};

// =======================
// Controlador
// =======================

class Controlador {
private:
    Modelo modelo;
    Vista vista;

public:
    void ejecutar() {
        int n, algoritmo;
        cout << "Ingrese el numero de politicos: ";
        cin >> n;

        if (n < 10) {
            cout << "El numero minimo es 10. Se asignara n = 10 por defecto." << endl;
            n = 10;
        }

        cout << "Seleccione el algoritmo de ordenamiento:\n";
        cout << "1. Bubble Sort\n";
        cout << "2. Selection Sort\n";
        cout << "Opcion: ";
        cin >> algoritmo;
        if (algoritmo != 1 && algoritmo != 2) {
            cout << "Opcion invalida. Se usara Bubble Sort por defecto.\n";
            algoritmo = 1;
        }

        modelo.generarPoliticos(n);
        vista.mostrarMatriz(modelo.matrizPoliticos, "Matriz de politicos generada:");

        modelo.ordenarPorEdad(algoritmo);
        vista.mostrarMatriz(modelo.matrizPoliticos, "Filas ordenadas por edad:");

        modelo.ordenarPorDineroYEdad(algoritmo);
        vista.mostrarMatriz(modelo.matrizPoliticos, "Filas por dinero y columnas por edad:");
    }
};

// =======================
// Main
// =======================

int main() {
    Controlador controlador;
    controlador.ejecutar();
    return 0;
}


