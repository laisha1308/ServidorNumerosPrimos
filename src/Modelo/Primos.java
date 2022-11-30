package Modelo;

public class Primos {
    private int arreglo[];

    public Primos(int arreglo[]) {
        this.arreglo = arreglo;
    }

    private boolean esPrimo(int numero) {
        for (int i = 2; i < (numero / 2) + 1; i++) {
            if (numero % i == 0)
                return false;
        }
        return true;
    }

    public String getPrimos() {
        String mensaje = "";
        for (int i = 0; i < arreglo.length; i++) {
            if (esPrimo(arreglo[i])) {
                mensaje += arreglo[i] + "\n";
            }
        }
        return mensaje;
    }
}