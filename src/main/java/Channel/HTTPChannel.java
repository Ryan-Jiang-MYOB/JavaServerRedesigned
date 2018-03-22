package Channel;

import CustomException.InvalidRequestException;
import Model.Request;
import Model.Response;
import Worker.RequestParser;
import Worker.ResponseParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HTTPChannel implements Channel {
    private Socket _clientSocket;
    private RequestParser _requestParser;
    private ResponseParser _responseParser;

    public HTTPChannel(Socket clientSocket, RequestParser requestParser, ResponseParser responseParser) {
        _clientSocket = clientSocket;
        _requestParser = requestParser;
        _responseParser = responseParser;
    }

    public Request fetch() throws IOException {
        BufferedReader _reader = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));
        try {
            return _requestParser.parseRequest(_reader);
        } catch (InvalidRequestException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String send(Response response) throws IOException {
        String responseString = _responseParser.parseResponse(response);
        OutputStream outputStream = _clientSocket.getOutputStream();
        outputStream.write(responseString.getBytes());
        outputStream.close();
        return outputStream.toString();
    }
}
