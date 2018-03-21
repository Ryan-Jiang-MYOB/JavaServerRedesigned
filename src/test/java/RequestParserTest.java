import Worker.HTTPRequestParser;
import Worker.RequestParser;
import CustomException.InvalidRequestException;
import Mocks.MockClientSocket;
import Model.Enum.HTTPRequestType;
import Model.Request;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestParserTest {


    @Test
    public void correctlyParsingHTTPRequestObject() throws IOException, InvalidRequestException {
        String requestLine = "POST /path HTTP/1.1\n";
        String headers = "Host: localhost:8888\n" +
                "Content-Type: application/json\n" +
                "Cache-Control: no-cache\n" +
                "content-length: 31\n" +
                "Postman-Token: 41a40579-8368-4f89-9b2b-b02c4475fafd\n" +
                "\n";
        String dummyBody = "{\n" +
                "\t\"name\": \"ryan\",\n" +
                "\t\"age\": 25\n" +
                "}";

        RequestParser parser = new HTTPRequestParser();
        Socket clientSocket = new MockClientSocket(requestLine, headers, dummyBody);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Request request = parser.parseRequest(reader);
        Assert.assertEquals(HTTPRequestType.POST, request.getType());
        Assert.assertEquals("/path", request.getPath().toString());
        Assert.assertEquals("localhost:8888", request.getHeader("Host"));
        Assert.assertEquals(dummyBody.replaceAll("\\s", ""), request.getBody());
    }
}
