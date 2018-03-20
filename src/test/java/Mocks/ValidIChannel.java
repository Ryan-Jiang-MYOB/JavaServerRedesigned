package Mocks;

import Channel.IChannel;
import Model.IRequest;
import Model.IResponse;

import java.net.Socket;

public class ValidIChannel implements IChannel {
    private Socket clientSocket;

    public ValidIChannel(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public IRequest fetch() {
        return new ValidHTTPRequest();
    }

    public String send(IResponse response) {
        return "";
    }
}
