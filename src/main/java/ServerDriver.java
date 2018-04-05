import Channel.*;
import Model.Request;
import Model.Response;
import Service.Controller;
import Worker.HTTPRequestParser;
import Worker.HTTPResponseParser;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;

public class ServerDriver implements Runnable {
    private Socket clientSocket;

    public ServerDriver(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public synchronized void run() {
        if (clientSocket.isConnected()) {
            Channel channel = new HTTPChannel(clientSocket, new HTTPRequestParser(), new HTTPResponseParser());
            Request request;

            try {
                Router router = new HTTPRouter();
                request = channel.fetch();
                if (request == null) {
                    return;
                }
                Controller controller = router.routeToController(request);
                Response response = controller.handleRequest(request);
                channel.send(response);

            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Couldn't Close Socket Connection");
                    e.printStackTrace();
                }
            }
        }
    }
}
