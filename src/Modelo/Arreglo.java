package Modelo;

import java.util.Arrays;

public class Arreglo {
    private int arreglo[], arreglosumado[];
    private boolean inicializado;

    public Arreglo() {
        this.inicializado = false;
    }

    public void arregloAleatorio(int largo) {
        int valor = 0;
        boolean existe;
        arreglo = new int[largo];
        for (int i = 0; i < arreglo.length; i++) {
            existe = false;
            valor = (int) ((Math.random() * largo * 100) + 2);
            for (int j = 0; j < i; j++) {
                if (arreglo[j] == valor) {
                    existe = true;
                    i--;
                    break;
                }
            }
            if (!existe) {
                arreglo[i] = valor;
            }
        }
        ordenar(0, arreglo.length - 1);
        inicializado = true;
    }

    public void sumar(int largo, Arreglo primerArreglo) {
        int arreglo1[], arreglo2[];
        arregloAleatorio(largo);
        arreglo1 = primerArreglo.getArreglo();
        arreglo2 = getArreglo();
        arreglo = new int[arreglo1.length + arreglo2.length];
        System.arraycopy(arreglo1, 0, arreglo, 0, arreglo1.length);
        System.arraycopy(arreglo2, 0, arreglo, arreglo1.length, arreglo2.length);
        ordenar(0, arreglo.length - 1);
    }

    private void ordenar(int izquierda, int derecha) {
        if (izquierda < derecha) {
            int centro = (izquierda + derecha) / 2;
            ordenar(izquierda, centro);
            ordenar(centro + 1, derecha);
            unir(izquierda, centro, derecha);
        }
    }

    private void unir(int izquierda, int centro, int derecha) {
        int indexL = 0;
        int indexR = 0;
        int index = izquierda;
        int lengthL = centro - izquierda + 1;
        int lengthR = derecha - centro;
        int arrayL[] = new int [lengthL];
        int arrayR[] = new int [lengthR];

        for (int i = 0; i < lengthL; i++) {
            arrayL[i] = arreglo[izquierda + i];
        }
        for (int i = 0; i < lengthR; i++) {
            arrayR[i] = arreglo[centro + 1 + i];
        }

        while (indexL < lengthL && indexR < lengthR) {
            if (arrayL[indexL] <= arrayR[indexR]) {
                arreglo[index] = arrayL[indexL];
                indexL++;
            } else {
                arreglo[index] = arrayR[indexR];
                indexR++;
            }
            index++;
        }

        while (indexL < lengthL) {
            arreglo[index] = arrayL[indexL];
            indexL++;
            index++;
        }

        while (indexR < lengthR) {
            arreglo[index] = arrayR[indexR];
            indexR++;
            index++;
        }
    }

    public String imprimirArreglo() {
        String mensaje = "";
        for (int i = 0; i < arreglo.length; i++) {
            mensaje += arreglo[i] + "\n";
        }
        return mensaje;
    }

    public int[] getArreglo() {
        return Arrays.copyOf(this.arreglo, this.arreglo.length);
    }

    public boolean isInicializado() {
        return inicializado;
    }

    public void reset() {
        this.inicializado = false;
    }
}
