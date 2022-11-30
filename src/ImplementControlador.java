import Modelo.Arreglo;
import Modelo.Primos;
import Modelo.PrimosES;
import Modelo.PrimosFJ;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ForkJoinPool;

public class ImplementControlador extends UnicastRemoteObject implements Controlador {
    private Arreglo arregloFinal, arregloAuxiliar;
    private long inicio;
    private long fin;
    protected ImplementControlador() throws RemoteException {
        super();
        this.arregloFinal = new Arreglo();
        this.arregloAuxiliar = new Arreglo();
    }

    public void guardar(int largo) {
        if (!arregloAuxiliar.isInicializado()) {
            arregloAuxiliar.arregloAleatorio(largo);
        } else {
            arregloFinal.sumar(largo, arregloAuxiliar);
        }
    }

    public String imprimir() {
        if (arregloFinal.isInicializado()) {
            return arregloFinal.imprimirArreglo();
        } else {
            return arregloAuxiliar.imprimirArreglo();
        }
    }

    public void limpiar()  {
        arregloFinal.reset();
        arregloAuxiliar.reset();
    }

    public String buscarSecuencial() {
        String primosOrdenados;
        this.inicio = System.currentTimeMillis();
        Primos primos = new Primos(arregloFinal.getArreglo());
        primosOrdenados = primos.getPrimos();
        this.fin = System.currentTimeMillis();
        return primosOrdenados;
    }

    public String buscarForkJoin() {
        String primosOrdenados;
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        this.inicio = System.currentTimeMillis();
        primosOrdenados = pool.invoke(new PrimosFJ(arregloFinal.getArreglo()));
        this.fin = System.currentTimeMillis();
        return primosOrdenados;
    }

    public String buscarExecutor() {
        String primosOrdenados;
        this.inicio = System.currentTimeMillis();
        PrimosES primos = new PrimosES(arregloFinal.getArreglo());
        primos.calcularPrimos();
        primosOrdenados = primos.getPrimos();
        this.fin = System.currentTimeMillis();
        return primosOrdenados;
    }

    public long getTiempo() {
        return fin - inicio;
    }

    public boolean verificarArregloFinal() throws RemoteException {
        return arregloFinal.isInicializado();
    }

    public boolean verificarArregloAuxiliar() throws RemoteException {
        return arregloAuxiliar.isInicializado();
    }
}
