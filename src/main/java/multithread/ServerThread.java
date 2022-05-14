package multithread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    // the constructor argument is an established socket
    public ServerThread(Socket s) {
        connectionSocket = s;
        try {
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));
            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String[] clientNumbers;
        Integer x;
        Integer y;
        Integer sum;

        try {
            // Legge una linea (terminata da \n) dal client
            clientNumbers = inFromClient.readLine().split(" ");
            x = Integer.valueOf(clientNumbers[0]);
            y = Integer.valueOf(clientNumbers[1]);

            // Aspetta per 10 secondi
            Thread.sleep(10000);

            // Calcola la somma
            sum = x + y;

            // Invia la risposta al client
            outToClient.writeBytes(String.valueOf(sum) + '\n');

            connectionSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
