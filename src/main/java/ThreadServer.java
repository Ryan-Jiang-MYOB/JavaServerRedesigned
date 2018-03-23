import Channel.*;
import Model.Request;
import Model.Response;
import Service.Controller;
import Worker.HTTPRequestParser;
import Worker.HTTPResponseParser;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;

public class ThreadServer implements Runnable {
    private Socket clientSocket;

    public ThreadServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public synchronized void run() {
        if (clientSocket.isConnected()) {
            Channel channel = new HTTPChannel(clientSocket, new HTTPRequestParser(), new HTTPResponseParser());
            Request request;

            try {
                request = channel.fetch();
                if (request == null) {
                    System.out.println("Empty Request");
                    return;
                }
                Router router = new HTTPRouter();
                Controller controller = router.routeToController(request);
                Response response = controller.doGet(request);
                channel.send(response);
            } catch (IOException e) {
                System.err.println("Couldn't Establish Service Socket on port: 8080");
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                    System.out.println("Helloooooooo");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
