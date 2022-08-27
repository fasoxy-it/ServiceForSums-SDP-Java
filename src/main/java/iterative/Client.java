package iterative;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String argv[]) throws Exception {

        String numbers;
        String numbersSum;

        // Inizializza l'input stream (da tastiera)
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        // Inizializza una socket client, connessa al server
        Socket clientSocket = new Socket("localhost", 6789);

        System.out.println("\n CLIENT STARTS");

        // Inizializza lo stream di output verso la socket
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        // Inizializza lo stream di input dalla socket
        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));

        // Legge una linea da tastiera
        numbers = inFromUser.readLine();

        // Invia la linea al server
        outToServer.writeBytes(numbers + '\n');

        // Legge la risposta inviata dal server (linea terminata da \n)
        numbersSum = inFromServer.readLine();
        System.out.println("\n FROM SERVER: " + numbersSum);

        clientSocket.close();
    }
}
