package Mocks;

import Channel.Channel;
import Model.Enum.HTTPRequestType;
import Model.Request;
import Model.Response;

import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class ValidChannel implements Channel {
    private Socket clientSocket;

    public ValidChannel(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Request fetch() {
        MockRequestFactory mockRequestFactory = null;
        try {
            mockRequestFactory = new MockRequestFactory();
            return mockRequestFactory.getValidRequest(HTTPRequestType.POST, new URI("log"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean send(Response response) {
        return true;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
