package Modelo;

public class Hilo implements Runnable {
    PrimosES primos;
    private int valor;

    Hilo(int valor, PrimosES primos) {
        this.primos = primos;
        this.valor = valor;
    }

    private boolean esPrimo(int numero) {
        for (int i = 2; i < (numero / 2) + 1; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        if (esPrimo(valor)) {
            String mensaje = primos.getPrimos();
            mensaje += valor + "\n";
            primos.setPrimos(mensaje);
        }
    }
}
