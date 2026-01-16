package servidor;

import modelo.canciones;
import java.io.*;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
    private Socket enchufeAlCliente;
    private canciones canciones;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public HiloEscuchador(Socket cliente, canciones canciones) throws IOException {
        this.enchufeAlCliente = cliente;
        this.canciones = canciones;
    }

    @Override
    public void run() {
        try {
            salida = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
            entrada = new ObjectInputStream(enchufeAlCliente.getInputStream());
            String peticion = "";

            while (!"5".equals(peticion)) {
                peticion = (String) entrada.readObject();

                switch (peticion) {
                    case "1":
                        salida.writeObject(canciones.cancionAzar());
                        break;
                    case "2":
                        salida.writeObject(canciones.getCancionesGrupo((String) entrada.readObject()));
                        break;
                    case "3":
                        salida.writeObject(canciones.getCancionesTitulo((String) entrada.readObject()));
                        break;
                    case "4":
                        salida.writeObject(canciones.getListaDistribucion());
                        break;
                    case "5":
                        salida.writeObject("FIN");
                        break;
                    default:
                        salida.writeObject("Opción no válida");
                }
                salida.flush();
            }

            entrada.close();
            salida.close();
            enchufeAlCliente.close();
            System.out.println("Cliente desconectado");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en hilo: " + e.getMessage());
        }
    }
}
