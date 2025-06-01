import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public void run() throws IOException {
        int serverPort = 8011; // server port
        InetAddress serverAddress = InetAddress.getByName(
                "localhost"); // server is available on local maching
        Socket socket = new Socket(serverAddress, serverPort);

        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        toServer.println("Hello from the client !!");
        String serverResponse = fromServer.readLine();
        System.out.println("Response from the Server is: " + serverResponse);

        socket.close();
        toServer.close();
        fromServer.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
