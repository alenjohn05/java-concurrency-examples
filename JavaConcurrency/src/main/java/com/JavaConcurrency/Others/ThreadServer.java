package JavaConcurrency.src.main.java.com.JavaConcurrency.Others;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadServer {
    public ThreadServer(int maxThreads) {
        executor = Executors.newFixedThreadPool(maxThreads);
    }

    private final ExecutorService executor;

    private void handleRequest(ClientRequest request) {
        executor.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(
                    "Request from client " + request.getClientId() + " processed." + Thread.currentThread().getName());

        });
    }

    private void shutdown() {
        executor.shutdown();
        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
            executor.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadServer server = new ThreadServer(3); // Create a server with a maximum of 3 threads

        // Simulate incoming client requests
        for (int i = 0; i < 5; i++) {
            ClientRequest request = new ClientRequest(i);
            server.handleRequest(request);
        }

        // Shutdown the server after processing all requests
        server.shutdown();
    }

}

class ClientRequest {
    private final int clientId;

    public ClientRequest(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }
}
