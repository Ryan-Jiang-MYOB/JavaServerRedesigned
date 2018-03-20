import Channel.HTTPChannel;
import Channel.HTTPRequestParser;
import Channel.HTTPResponseParser;
import Channel.IChannel;
import Model.Enum.HTTPResponseStatus;
import Model.HTTPResponse;
import Model.IRequest;
import Model.IResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class JavaServerEntry {
//
//    public static IResponse getValidResponse() {
//        final String validHTTPProtocol = "HTTP/1.1";
//        final HTTPResponseStatus validHTTPStatus = HTTPResponseStatus.OK;
//        final Map validHeadersMap = new LinkedHashMap();
//        final String validBody = "{\n" +
//                "\t\"name\": \"ryan\",\n" +
//                "\t\"age\": 25\n" +
//                "}";
//        validHeadersMap.put("Content-Type", "application/json");
//        validHeadersMap.put("content-length", "24");
//        return new HTTPResponse(validHTTPProtocol, validHTTPStatus, validHeadersMap, validBody);
//    }
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                IChannel channel = new HTTPChannel(clientSocket, new HTTPRequestParser(), new HTTPResponseParser());
                IRequest request = channel.fetch();
                if (request != null) {
                    System.out.println(request.getBody());
                }
            }
        } catch (IOException e) {
            System.err.println("Couldn't Establish Server Socket on port: 8080");
            e.printStackTrace();
        }
    }
}
