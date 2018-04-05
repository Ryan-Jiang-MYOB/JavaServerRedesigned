import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServerEntry {

    public static void main(String[] args) {
        final int port = Integer.getInteger(args[0]);
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
