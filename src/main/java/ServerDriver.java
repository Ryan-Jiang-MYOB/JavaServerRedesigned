import Channel.*;
import Model.Request;
import Model.Response;
import Service.RequestHandler;
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
                Thread.currentThread().sleep(100); // Postman stuff hack.
                Router router = new HTTPRouter();
                if (!channel.isAvailable()) {
                    return;
                }
                request = channel.fetch();
                RequestHandler requestHandler = router.routeToController(request);
                Response response = requestHandler.handleRequest(request);
                channel.send(response);

            } catch (URISyntaxException | InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                closeClientSocket();
            }
        }
    }

    private void closeClientSocket() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Couldn't Close Socket Connection");
            e.printStackTrace();
        }
    }
}
