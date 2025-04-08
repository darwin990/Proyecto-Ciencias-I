#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <iomanip>
#include <sstream>
#include <string>

using namespace std;

// =================== MODELO ===================
struct Politico {
    int id;
    int dineroRobado;
    string fechaNacimiento;
};

class Modelo {
public:
    vector<Politico> politicos;

    void generarPoliticos(int n) {
        srand(time(0));
        politicos.clear();
        for (int i = 0; i < n; ++i) {
            Politico p;
            p.id = i + 1;
            p.dineroRobado = rand() % 1000000 + 100;
            p.fechaNacimiento = generarFechaAleatoria();
            politicos.push_back(p);
        }
    }

    void ordenarPoliticos(string metodo) {
        if (metodo == "bubble") {
            bubbleSort();
        } else if (metodo == "selection") {
            selectionSort();
        } else {
            cout << "Metodo de ordenamiento invalido. Se usara Bubble Sort por defecto." << endl;
            bubbleSort();
        }
    }

private:
    void bubbleSort() {
        bool swapped;
        for (size_t i = 0; i < politicos.size() - 1; ++i) {
            swapped = false;
            for (size_t j = 0; j < politicos.size() - i - 1; ++j) {
                if (politicos[j].dineroRobado > politicos[j + 1].dineroRobado) {
                    swap(politicos[j], politicos[j + 1]);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    void selectionSort() {
        int n = politicos.size();
        for (int i = 0; i < n - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (politicos[j].dineroRobado < politicos[minIndex].dineroRobado) {
                    minIndex = j;
                }
            }
            swap(politicos[i], politicos[minIndex]);
        }
    }

    string intToString(int num) {
        stringstream ss;
        ss << num;
        return ss.str();
    }

    string generarFechaAleatoria() {
        int dia = rand() % 28 + 1;
        int mes = rand() % 12 + 1;
        int anio = rand() % 50 + 1950;
        return intToString(dia) + "/" + intToString(mes) + "/" + intToString(anio);
    }
};

// =================== VISTA ===================
class Vista {
public:
    void mostrarPoliticos(const vector<Politico>& politicos, const string& mensaje) {
        cout << mensaje << endl;
        cout << left << setw(5) << "ID" << setw(15) << "Dinero Robado" << "Fecha de Nacimiento" << endl;
        cout << "---------------------------------------" << endl;
        for (size_t i = 0; i < politicos.size(); ++i) {
            cout << left << setw(5) << politicos[i].id << setw(15) << politicos[i].dineroRobado << politicos[i].fechaNacimiento << endl;
        }
        cout << "---------------------------------------" << endl;
    }

    string pedirMetodoOrdenamiento() {
        string metodo;
        cout << "Seleccione el algoritmo de ordenamiento (bubble / selection): ";
        cin >> metodo;
        return metodo;
    }
};

// =================== CONTROLADOR ===================
class Controlador {
private:
    Modelo modelo;
    Vista vista;

public:
    void ejecutar() {
        int n;
        cout << "Ingrese el numero de politicos (minimo 10): ";
        cin >> n;
        if (n < 10) {
            cout << "El numero minimo de politicos es 10. Se establecera n = 10 por defecto." << endl;
            n = 10;
        }

        string metodo = vista.pedirMetodoOrdenamiento();

        modelo.generarPoliticos(n);
        vista.mostrarPoliticos(modelo.politicos, "\nLista de politicos generada:");

        modelo.ordenarPoliticos(metodo);
        vista.mostrarPoliticos(modelo.politicos, "\nLista de politicos ordenada por dinero robado:");
    }
};

// =================== MAIN ===================
int main() {
    Controlador controlador;
    controlador.ejecutar();
    return 0;
}

