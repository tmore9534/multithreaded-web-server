import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    static int request_counter = 0;

    public Runnable getRunnable() throws IOException {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8081;
                try {
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);

                    try (
                            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader fromSocket = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()))) {
                        toSocket.println("Hello from Client " + socket.getLocalSocketAddress());
                        request_counter += 1;
                        System.out.println(request_counter);

                        String line = fromSocket.readLine();
                        System.out.println("Response from Server " + line);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // The socket will be closed automatically when leaving the try-with-resources
                    // block
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    public static void main(String[] args) {
        Client cl = new Client();

        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(cl.getRunnable());
                thread.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
