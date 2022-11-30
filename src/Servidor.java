import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args) {
        String IP = "192.168.1.111";
        int PUERTO = 8080;
        try {
            System.setProperty("java.rmi.server.hostname", IP);
            Registry registry= LocateRegistry.createRegistry(PUERTO);
            registry.rebind("NumerosPrimos", new ImplementControlador());
            System.out.println("Servidor iniciado con la IP: " + IP);
        } catch (RemoteException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}