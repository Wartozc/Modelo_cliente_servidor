

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * <h1>Clase servidor</h1>
 * <hr>
 * <p>
 *     Esta clase permite la creación de un servidor centralizado y define las diferentes instancias de los clientes
 *     que realizan solicitudes a este y también permite responder los mensajes de los clientes.
 * </p>
 * <br>
 */
public class Server {
    public static void main(String[] args) {

        int port; // Esta variable se crea con el fin de definir el puerto en el cual se va a levantar el servidor
        Scanner sc = new Scanner(System.in); // Se define que se necesita leer lo que escribe el usuario

        System.out.println("Puerto del servidor"); //Acá se solicita el puerto del servidor
        port = sc.nextInt(); // Se lee el puerto del servidor

        try (ServerSocket serverSocket = new ServerSocket(port)) { // En esta Linea se define la instancia del servidor
            //y se levanta en el puerto especificado por el usuario

            System.out.println("Servidor escuchando en el puerto " + port); // Acá se le dice al usuario en qué
            // puerto se ha levantado el usuario, simplemente se confirma que el servidor está arriba

            while (true) { // En esta línea se levanta el servidor por siempre
                Socket clientSocket = serverSocket.accept(); // En esta línea se especifica que se aceptan las solicitudes
                // o conexiones de diferentes clientes
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress()); // En esta línea se especifica
                // el cliente que se ha conectado al servidor
                new ClientHandler(clientSocket).start(); // Se define la creación de un cliente en un hilo independiente
            }
        } catch (IOException e) { // Se captura la excepción en caso de presentarse
            e.printStackTrace(); // Se muestra la excepción que se haya presentado
        }
    }
}