import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class JavaServerEntry {

    public static void main(String[] args) {
        String directory;
        int port = 5000;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                port = Integer.parseInt(args[i+1]);
            }
            if (args[i].equals("-d")) {
                directory = args[i+1];
            }
        }

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(new ServerDriver(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Couldn't Establish Service Socket on port: " + port);
            System.err.println(e.getCause());
            e.printStackTrace();
        }
    }
}
