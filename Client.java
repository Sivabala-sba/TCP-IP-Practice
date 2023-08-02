import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            String serverIP = "10.11.152.128"; // Replace with the server's IP address
            int serverPort = 100; // Replace with the server's port number

            Socket clientSocket = new Socket(serverIP, serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter message to send to server: ");
                String message = consoleInput.readLine();

                out.println(message);

                String response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}