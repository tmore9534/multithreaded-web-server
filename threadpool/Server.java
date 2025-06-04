import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Server {
    private final ExecutorService threadPool;
    private static final AtomicInteger request_counter = new AtomicInteger(0); // thread-safe

    Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        try (PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {
            toSocket.println("Hello from the server ");
            System.out.println(request_counter.incrementAndGet());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8011;
        int poolSize = 100;
        Server server = new Server(poolSize);

        try {
            ServerSocket serverSocket = new ServerSocket(port, 2048);
            serverSocket.setSoTimeout(50000);

            while (true) {
                System.out.println("Server is listening on the port " + port);
                Socket acceptedSocket = serverSocket.accept();
                server.threadPool.execute(() -> server.handleClient(acceptedSocket));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            server.threadPool.shutdown();
        }

    }
}
