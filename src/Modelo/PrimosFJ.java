package Modelo;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class PrimosFJ extends RecursiveTask<String> {
    private int arreglo[];

    public PrimosFJ(int[] arreglo) {
        this.arreglo = arreglo;
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
    protected String compute() {
        if (arreglo.length <= 10){
            String mensaje = "";
            for (int i = 0; i < arreglo.length; i++) {
                if (esPrimo(arreglo[i])) {
                    mensaje += arreglo[i] + "\n";
                }
            }
            return mensaje;
        } else {
            PrimosFJ izquierda = new PrimosFJ(Arrays.copyOfRange(arreglo, 0, arreglo.length / 2));
            izquierda.fork();
            PrimosFJ derecha = new PrimosFJ(Arrays.copyOfRange(arreglo, arreglo.length / 2, arreglo.length - 1));
            derecha.fork();
            return izquierda.join() + derecha.join();
        }
    }
}
