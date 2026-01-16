package servidor;

import modelo.canciones;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
    public static void main(String[] args) {
        canciones canciones = new canciones();
        System.out.println("SERVIDOR DE CANCIONES ENCENDIDO");
        System.out.println("--------------------------------");

        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("192.168.56.1", 2000);
            servidor.bind(direccion);
            System.out.println("Servidor escuchando en: " + direccion.getAddress());

            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Cliente conectado");
                new Thread(new HiloEscuchador(enchufeAlCliente, canciones)).start();
            }
        } catch (IOException e) {
            System.out.println("Error de servidor: " + e.getMessage());
        }
    }
}
