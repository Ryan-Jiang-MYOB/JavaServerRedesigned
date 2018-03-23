import Mocks.MockRequestFactory;
import Worker.HTTPRequestParser;
import Worker.RequestParser;
import CustomException.InvalidRequestException;
import Mocks.MockClientSocket;
import Model.Enum.HTTPRequestType;
import Model.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Map;

public class HTTPRequestParserTest {
    MockRequestFactory requestFactory;
    String validRequestLine, invalidRequestLineWithTwoFields;
    String validPostHeaders;
    String validDummyBody;

    RequestParser parser;

    @Before
    public void setup() throws URISyntaxException {
        requestFactory = new MockRequestFactory();
        validRequestLine = HTTPRequestType.POST.getRequestTypeText() + " "
                + requestFactory.validLogURI.toString() + " "
                + requestFactory.validHTTPProtocol + "\n";
        invalidRequestLineWithTwoFields = HTTPRequestType.POST.getRequestTypeText() + " "
                + requestFactory.validLogURI.toString() + "\n";
        validPostHeaders = requestFactory.validPostHeaderString;
        validDummyBody = requestFactory.validPostDummyBody;

        parser = new HTTPRequestParser();
    }

    @Test
    public void correctlyParsingHTTPRequestObject() throws IOException, InvalidRequestException {

        Socket clientSocket = new MockClientSocket(validRequestLine, validPostHeaders, validDummyBody);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Request request = parser.parseRequest(reader);
        Assert.assertEquals(HTTPRequestType.POST, request.getType());
        Assert.assertEquals("/log", request.getPath().toString());
        Assert.assertEquals("localhost:8888", request.getHeader("host"));
        Assert.assertEquals(validDummyBody.replaceAll("\\s", ""), request.getBody());
    }

    @Test (expected = InvalidRequestException.class)
    public void throwExceptionWhenParsingInvalidRequestLine() throws IOException, InvalidRequestException {

        Socket clientSocket = new MockClientSocket(invalidRequestLineWithTwoFields, validPostHeaders, validDummyBody);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Request request = parser.parseRequest(reader);
        Assert.assertNull(request);
    }

    @Test (expected = InvalidRequestException.class)
    public void throwExceptionWhenParsingInvalidRequestType() throws IOException, InvalidRequestException {

        String invalidRequestLineWithWrongType = "FOO /path HTTP/1.1\n";
        Socket clientSocket = new MockClientSocket(invalidRequestLineWithWrongType, validPostHeaders, validDummyBody);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Request request = parser.parseRequest(reader);
        Assert.assertNull(request);
    }

    @Test (expected = InvalidRequestException.class)
    public void throwExceptionWhenParsingInvalidRequestURI() throws IOException, InvalidRequestException {

        String invalidRequestLineWithWrongURI = "POST ***ADS1@#!@#!@%$^%$&%*( HTTP/1.1\n";
        Socket clientSocket = new MockClientSocket(invalidRequestLineWithWrongURI, validPostHeaders, validDummyBody);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Request request = parser.parseRequest(reader);
        Assert.assertNull(request);
    }
}
