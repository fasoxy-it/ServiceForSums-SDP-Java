package iterative;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class IterativeServer {
    public static void main(String argv[]) throws Exception {
        String[] clientNumbers;
        Integer x;
        Integer y;
        Integer sum;

        // Crea una "listening socket" sulla porta specificata
        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("\n SERVER STARTS");

        while(true) {
			/*
				Viene chiamata accept (bloccante).
				All'arrivo di una nuova connessione crea una nuova
				"established socket"
			*/
            Socket connectionSocket = welcomeSocket.accept();

            // Inizializza lo stream di input dalla socket
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream()));

            // Inizializza lo stream di output verso la socket
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            // Legge una linea (terminata da \n) dal client
            clientNumbers = inFromClient.readLine().split(" ");
            x = Integer.valueOf(clientNumbers[0]);
            y = Integer.valueOf(clientNumbers[1]);

            // Calcola la somma
            sum = x + y;

            // Invia la risposta al client
            outToClient.writeBytes(String.valueOf(sum) + '\n');

            connectionSocket.close();
        }
    }
}
