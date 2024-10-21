import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class severtwo {
    public static void main(String[] args) {
        int port = 12345; // Server port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            // Accept incoming connection
            try (Socket clientSocket = serverSocket.accept();
                 InputStream inputStream = clientSocket.getInputStream();
                 FileOutputStream fileOutputStream = new FileOutputStream("received.file")) {

                System.out.println("Connection established. Receiving file...");

                // Read data from input stream and write it to a file
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File received and saved as 'received.file'");
            } catch (IOException e) {
                System.err.println("Error while receiving file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error while setting up server: " + e.getMessage());
        }
    }
}
