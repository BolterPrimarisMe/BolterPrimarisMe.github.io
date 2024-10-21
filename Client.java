import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Assuming server and client are on the same machine
        int port = 23456; // Client port
        Scanner scanner = new Scanner(System.in);

        // Ask user for the file to send
        System.out.print("Enter the path of the file to send: ");
        String filePath = scanner.nextLine();

        try (Socket socket = new Socket(serverAddress, port);
             FileInputStream fileInputStream = new FileInputStream(filePath);
             OutputStream outputStream = socket.getOutputStream()) {

            System.out.println("Connecting to server...");

            // Read file and send to server
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            System.err.println("Error while sending file: " + e.getMessage());
        }
    }
}
