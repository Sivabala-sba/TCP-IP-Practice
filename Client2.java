import java.io.*;
import java.net.*;

public class Client2 {
    public static void main(String[] args) {
        try {
            String serverIP = "10.11.152.128"; // Replace with the server's IP address
            int serverPort = 123; // Replace with the server's port number

            Socket clientSocket = new Socket(serverIP, serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Create a separate thread to read messages from the server
            Thread serverReader = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverReader.start();

            while (true) {
                String message = consoleInput.readLine();
                out.println(message);

                if (message.equalsIgnoreCase("stop")) {
                    break;
                }
            }

            // Close connections
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}