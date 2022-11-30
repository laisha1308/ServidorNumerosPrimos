package Modelo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimosES {
    PrimosES primos;
    private int arreglo[];
    private String mensaje = "";

    PrimosES() { /* Constructor vacío */ }

    public PrimosES(int arreglo[]) {
        primos = new PrimosES();
        this.arreglo = arreglo;
    }

    public void calcularPrimos() {
        ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < arreglo.length; i++) {
            executor.execute(new Hilo(arreglo[i], primos));
        }
        executor.shutdown();
        mensaje = primos.getPrimos();
    }

    public synchronized void setPrimos(String mensaje) {
        this.mensaje = mensaje;
    }

    public synchronized String getPrimos() {
        return mensaje;
    }
}
