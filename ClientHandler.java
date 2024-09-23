

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * <h1>Clase Administrador de cliente</h1>
 * <hr>
 * <p>
 *     Esta clase permite la creación de diferentes clientes, utilizando los hilos disponibles para la ejecución
 * </p>
 * <br>
 */
class ClientHandler extends Thread { // Acá se hereda la clase Thread que permite la ejecución de los clientes
    // en diferentes hilos de la CPU
    private Socket socket; // Se define la instancia del socket

    public ClientHandler(Socket socket) { // En el constructor, se le asigna el socket a la propiedad creada
        // anteriormente, o a la variable o atributo de clase
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             // En la línea anterior se define un lector de la consola y se especifica que se va a leer lo que
             // mande el socket, esto permite leer los mensajes del client
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            // En la línea anterior se define la instancia que va a permitir mandarle mensajes al cliente desde el servidor
            String message; // Acá se define el mensaje que llega desde el cliente
            while ((message = in.readLine()) != null) { // Permite leer el mensaje que manda el cliente
                System.out.println(String.format("El cliente %s dice: %s", socket.getInetAddress(), message));
                // En la línea anterior se muestra el mensaje que manda el cliente al servidor
                Scanner sc = new Scanner(System.in); // En esta línea se define el lector de la consola
                String messageServer = sc.nextLine(); // Se lee lo que está escribiendo el servidor
                out.println(messageServer); // En esta línea se le manda la respuesta del servidor al cliente
            }
        } catch (IOException e) { // Se captura la excepción en caso de presentarse
            e.printStackTrace(); // Se muestra la excepción que se haya presentado
        } finally {
            try {
                System.out.println(String.format("El cliente %s Se ha desconectado", socket.getInetAddress()));
                // En la línea anterior se especifica que el cliente ha finalizado la sesión
                socket.close(); // En esta línea se cierra la conexión con el cliente
            } catch (IOException e) { // Se captura la excepción en caso de presentarse
                e.printStackTrace(); // Se muestra la excepción que se haya presentado
            }
        }
    }
}