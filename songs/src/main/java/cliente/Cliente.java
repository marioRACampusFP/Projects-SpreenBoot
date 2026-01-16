package cliente;

import modelo.cancion;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private static ObjectInputStream entrada;
    private static ObjectOutputStream salida;

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        try {
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("192.168.56.1", 2000);
            System.out.println("Conectando al servidor...");
            cliente.connect(direccionServidor);
            System.out.println("Conexión establecida con el servidor");

            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());

            String opcion = "";
            while (!opcion.equals("5")) {
                System.out.println("1. Una sola canción");
                System.out.println("2. Canciones de un grupo");
                System.out.println("3. Canciones por título");
                System.out.println("4. Todas las canciones de la lista");
                System.out.println("5. Desconectar");
                System.out.print("Elige una opción: ");
                opcion = lector.nextLine();

                salida.writeObject(opcion);

                switch (opcion) {
                    case "1":
                        System.out.println(entrada.readObject());
                        break;
                    case "2":
                        System.out.print("Introduce el grupo: ");
                        salida.writeObject(lector.nextLine());
                        mostrarLista((ArrayList<?>) entrada.readObject());
                        break;
                    case "3":
                        System.out.print("Introduce parte del título: ");
                        salida.writeObject(lector.nextLine());
                        mostrarLista((ArrayList<?>) entrada.readObject());
                        break;
                    case "4":
                        mostrarLista((ArrayList<?>) entrada.readObject());
                        break;
                    case "5":
                        System.out.println("Desconectando...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
                System.out.println();
            }

            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        lector.close();
    }

    private static void mostrarLista(ArrayList<?> lista) {
        if (lista.isEmpty()) {
            System.out.println("No se encontraron canciones.");
        } else {
            for (Object c : lista) {
                System.out.println(c);
            }
        }
    }
}
