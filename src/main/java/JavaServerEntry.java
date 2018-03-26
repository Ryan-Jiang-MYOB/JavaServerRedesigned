import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServerEntry {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while(true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(new ServerDriver(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Couldn't Establish Service Socket on port: 8080");
            e.printStackTrace();
        }
    }
}
