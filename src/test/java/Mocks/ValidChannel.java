package Mocks;

import Channel.Channel;
import Model.Request;
import Model.Response;

import java.net.Socket;

public class ValidChannel implements Channel {
    private Socket clientSocket;

    public ValidChannel(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Request fetch() {
        return new ValidHTTPRequest();
    }

    public boolean send(Response response) {
        return true;
    }
}
