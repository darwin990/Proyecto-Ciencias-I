#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <sstream>
#include <cmath>
#include <string>
#include <iomanip>

using namespace std;

// =======================
// MODELO
// =======================

struct Politico {
    int id;
    int dineroRobado;
    string fechaNacimiento;
    int edad;
};

class Modelo {
public:
    vector<Politico> arrayPoliticos;
    vector< vector<Politico> > matrizPoliticos;
    int k, m;

    void generarArray(int n) {
        arrayPoliticos.clear();
        for (int i = 0; i < n; ++i) {
            Politico p;
            p.id = i + 1;
            p.dineroRobado = rand() % 1000000 + 100;
            p.edad = rand() % 50 + 30;
            p.fechaNacimiento = intToString(2024 - p.edad);
            arrayPoliticos.push_back(p);
        }
    }

    void generarMatriz(int n) {
        matrizPoliticos.clear();
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

    void ordenarArray(int algoritmo) {
        if (algoritmo == 1)
            bubbleSort(arrayPoliticos, false); // Ordena por dinero robado
        else
            selectionSort(arrayPoliticos, false); // Ordena por dinero robado
    }

    void ordenarMatriz(int algoritmo) {
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

    void bubbleSort(vector<Politico>& vec, bool ordenarPorEdad) {
        for (int i = 0; i < (int)vec.size() - 1; ++i) {
            for (int j = 0; j < (int)vec.size() - i - 1; ++j) {
                if ((ordenarPorEdad && vec[j].edad > vec[j + 1].edad) ||
                    (!ordenarPorEdad && vec[j].dineroRobado > vec[j + 1].dineroRobado)) {
                    Politico temp = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = temp;
                }
            }
        }
    }

    void selectionSort(vector<Politico>& vec, bool ordenarPorEdad) {
        for (int i = 0; i < (int)vec.size() - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < (int)vec.size(); ++j) {
                if ((ordenarPorEdad && vec[j].edad < vec[minIndex].edad) ||
                    (!ordenarPorEdad && vec[j].dineroRobado < vec[minIndex].dineroRobado)) {
                    minIndex = j;
                }
            }
            Politico temp = vec[i];
            vec[i] = vec[minIndex];
            vec[minIndex] = temp;
        }
    }

    void insertionSort(vector<Politico>& fila, bool ordenarPorEdad) {
        for (size_t i = 1; i < fila.size(); ++i) {
            Politico actual = fila[i];
            int j = i - 1;
            while (j >= 0 &&
                   ((ordenarPorEdad && fila[j].edad > actual.edad) ||
                    (!ordenarPorEdad && fila[j].dineroRobado > actual.dineroRobado))) {
                fila[j + 1] = fila[j];
                j--;
            }
            fila[j + 1] = actual;
        }
    }
};

// =======================
// VISTA
// =======================

class Vista {
public:
    void mostrarArray(vector<Politico> vec, string mensaje) {
        cout << mensaje << endl;
        cout << left << setw(5) << "ID" << setw(15) << "Dinero Robado" << setw(10) << "Edad" << "Fecha" << endl;
        cout << "---------------------------------------------" << endl;
        for (int i = 0; i < (int)vec.size(); ++i) {
            cout << left << setw(5) << vec[i].id
                 << setw(15) << vec[i].dineroRobado
                 << setw(10) << vec[i].edad
                 << vec[i].fechaNacimiento << endl;
        }
        cout << "---------------------------------------------" << endl;
    }

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
// CONTROLADOR
// =======================

class Controlador {
private:
    Modelo modelo;
    Vista vista;

public:
    void ejecutar() {
        int n, estructura, algoritmo;
        cout << "Ingrese el numero de politicos (minimo 10): ";
        cin >> n;
        if (n < 10) {
            cout << "Se asignara n = 10 por defecto." << endl;
            n = 10;
        }

        cout << "Seleccione la estructura de datos:\n1. Array\n2. Matriz\nOpcion: ";
        cin >> estructura;

        cout << "Seleccione el algoritmo de ordenamiento:\n1. Bubble Sort\n2. Selection Sort\nOpcion: ";
        cin >> algoritmo;
        if (algoritmo != 1 && algoritmo != 2) {
            cout << "Opcion invalida. Se usara Bubble Sort por defecto." << endl;
            algoritmo = 1;
        }

        if (estructura == 1) {
            modelo.generarArray(n);
            vista.mostrarArray(modelo.arrayPoliticos, "\nArray de politicos generado:");
            modelo.ordenarArray(algoritmo);
            vista.mostrarArray(modelo.arrayPoliticos, "\nArray ordenado por dinero robado:");
        } else {
            modelo.generarMatriz(n);
            vista.mostrarMatriz(modelo.matrizPoliticos, "\nMatriz de politicos generada:");
            modelo.ordenarMatriz(algoritmo);
            vista.mostrarMatriz(modelo.matrizPoliticos, "\nMatriz ordenada con Filas por dinero robado y columnas por edad:");
        }
    }
};

// =======================
// MAIN
// =======================

int main() {
    srand(time(0));
    Controlador controlador;
    controlador.ejecutar();
    return 0;
}



