import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int request_counter = 0;

    public void run() {
        try {
            int port_number = 8011;
            ServerSocket serverSocket = new ServerSocket(port_number);
            serverSocket.setSoTimeout(20000);

            while (true) {
                System.out.println("Server is listening on port " + port_number);
                Socket acceptedConnection = serverSocket.accept();
                System.out
                        .println("Connection accepted from the client " + acceptedConnection.getRemoteSocketAddress());

                PrintWriter toclient = new PrintWriter(acceptedConnection.getOutputStream(), true); // writer
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream())); // reader
                request_counter += 1;
                System.out.println(request_counter);
                String clientMessage = fromClient.readLine();
                System.out.println("Received from the client: " + clientMessage);

                toclient.println("Hello from the server");
                toclient.close();
                fromClient.close();
                acceptedConnection.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
