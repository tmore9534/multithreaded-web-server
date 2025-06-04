import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.function.Consumer;

public class Server {
    static int counter = 0;

    public Consumer<Socket> getConsumer() {
        return (acceptedConnSocket) -> {
            try {
                PrintWriter toclient = new PrintWriter(acceptedConnSocket.getOutputStream(), true); // writer
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnSocket.getInputStream())); // reader
                counter += 1;
                System.out.println("counter of no " + counter);
                // String clientMessage = fromClient.readLine();
                // System.out.println("Received from the client: " + clientMessage);
                toclient.println("Hello from the server");
                toclient.close();
                fromClient.close();
                acceptedConnSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public void run() throws IOException, SocketException {
        int port = 8011;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(20000);

        while (true) {
            System.out.println("Server is listening on the port" + port);
            Socket acceptedSocket = serverSocket.accept();

            Thread thread = new Thread(() -> this.getConsumer().accept(acceptedSocket));
            thread.start();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
