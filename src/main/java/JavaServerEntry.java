import Channel.*;
import Model.Request;
import Model.Response;
import Service.Controller;
import Worker.HTTPRequestParser;
import Worker.HTTPResponseParser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class JavaServerEntry {

    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while(true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(new ThreadServer(clientSocket)).start();
                System.out.println(Thread.activeCount());
            }
        } catch (IOException e) {
            System.err.println("Couldn't Establish Service Socket on port: 8080");
            e.printStackTrace();
        }
    }
}
