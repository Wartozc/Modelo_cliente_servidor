

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * <h1>Clase cliente</h1>
 * <hr>
 * <p>
 *     Esta clase permite la creación de un cliente; especificando el servidor y el puerto;
 *     A través de esta se pueden ejecutar interacciones escribiendo mensajes y recibiendo las respuestas del servidor
 * </p>
 * <br>
 */
public class Client {
    public static void main(String[] args) {
        String host; // Esta variable se va a utilizar como el host del servidor
        int port; // Acá se define el puerto del servidor

        Scanner sc = new Scanner(System.in); // Se define que se va a recibir lo que escriba el usuario
        System.out.println("Dirección del servidor"); // Se le solicita la dirección del servidor
        host = sc.nextLine(); // Se lee lo que el usuario escribe y se le asigna al host

        System.out.println("Puerto del servidor"); // se le solicita el puerto del servidor al usuario
        port = sc.nextInt(); // Se lee el puerto y se le asigna al puerto en el cual va a solicitar conexión al servidor

        try (Socket socket = new Socket(host, port); // Acá se levanta el socket, se crea el ciente y se conecta al servidor
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             // La línea anterior se utiliza para definir la instancia para el envío de mensajes al servidor
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             // La línea anterior se utilizar para definir que va a leer los mensajes del servidor
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            // La siguiente línea se utliza para especificar que va a leer lo que el usuaro escriba, para mandar
            // al servidor
            String userInput; // Se define la variable que va a almacenar el mensaje que el usuario va a mandar
            // al servidor
            System.out.println("Conectado al servidor. Escribe un mensaje:");
            // Está línea brinda la información de que el cliente se ha conectado al servidor y solicita el mensaje

            while ((userInput = stdIn.readLine()) != null) { // Acá se especifica que se va a estar mandando el mensaje
                // al servidor, en caso de que el usuario escriba
                if (userInput.equals("Salir")){
                    System.out.println("Se ha finalizado sesión");
                    socket.close();
                    break;
                }
                /*
                La validación anterior nos permite finalizar la conexión del ciente con el servidor
                cuando este escriba la palabra "Salir", Muestra el mensaje de salida, cierra la conexión
                y se sale del bucle while y finaliza la ejecución
                 */
                out.println(userInput); // En esta línea se manda el mensaje al servidor
                System.out.println("Respuesta del servidor: " + in.readLine()); // En esta línea se muestra la respuesta
                // del servidor
            }
        } catch (IOException e) { // Se captura la excepción en caso de presentarse
            e.printStackTrace(); // Se muestra la excepción que se haya presentado
        }
    }
}