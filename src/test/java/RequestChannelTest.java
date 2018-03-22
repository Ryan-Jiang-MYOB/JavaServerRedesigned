import Channel.*;
import CustomException.InvalidRequestException;
import Mocks.*;
import Model.Enum.HTTPRequestType;
import Model.HTTPRequest;
import Model.Request;
import Model.Response;
import Worker.HTTPRequestParser;
import Worker.HTTPResponseParser;
import Worker.RequestParser;
import Worker.ResponseParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class RequestChannelTest {
    Socket fakeClient;
    RequestParser requestParser;
    ResponseParser responseParser;
    String dummyBody;

    @Before
    public void setupClient() {
        String requestLine = "POST /path HTTP/1.1\n";
        String headers = "Host: localhost:8888\n" +
                "Content-Type: application/json\n" +
                "Cache-Control: no-cache\n" +
                "content-length: 24\n" +
                "Postman-Token: 41a40579-8368-4f89-9b2b-b02c4475fafd\n" +
                "\n";
        dummyBody = "{\"name\":\"ryan\",\"age\":25}";
        fakeClient = new MockClientSocket(requestLine, headers, dummyBody);
        requestParser = new HTTPRequestParser();
        responseParser = new HTTPResponseParser();
    }

    @Test
    public void fetchingDummyRequestFromClientSocket() throws IOException {
        Channel channel = new HTTPChannel(fakeClient, requestParser, responseParser);
        Request request = channel.fetch();
        Assert.assertNotNull(request);
    }

    @Test
    public void fetchingWithValidRequestAndGetParserCalledCorrectly() throws IOException {
        MockSpyValidRequestParser mockSpyValidRequestParser = new MockSpyValidRequestParser();
        Channel channel = new HTTPChannel(fakeClient, mockSpyValidRequestParser, responseParser);
        Request request = channel.fetch();
        Assert.assertEquals(1, mockSpyValidRequestParser.parseRequestCalled);
        Assert.assertNotNull(request);
        Assert.assertTrue(request instanceof HTTPRequest);
    }

    @Test
    public void fetchingWithInvalidRequestAndGettingNullWithOneParserCall() throws IOException {
        MockSpyInvalidRequestParser mockSpyInvalidRequestParser = new MockSpyInvalidRequestParser();
        Channel channel = new HTTPChannel(fakeClient, mockSpyInvalidRequestParser, responseParser);
        Request request = channel.fetch();
        Assert.assertEquals(1, mockSpyInvalidRequestParser.parseRequestCalled);
        Assert.assertNull(request);
    }

    @Test (expected = IOException.class)
    public void fetchingWithUnavailableSocket() throws IOException {
        MockSpyInvalidRequestParser mockSpyInvalidRequestParser = new MockSpyInvalidRequestParser();
        Channel channel = new HTTPChannel(new MockInvalidClientSocket(), mockSpyInvalidRequestParser, responseParser);
        Request request = channel.fetch();
        Assert.assertEquals(0, mockSpyInvalidRequestParser.parseRequestCalled);
        Assert.assertNull(request);
    }

//    @Test
//    public void fetchingValidRequestFromChannel() throws IOException, InvalidRequestException {
//        Channel channel = new HTTPChannel(fakeClient, requestParser, responseParser);
//        Request request = channel.fetch();
//        Assert.assertEquals(HTTPRequestType.POST, request.getType());
//        Assert.assertEquals(dummyBody, request.getBody());
//    }
//
//    @Test
//    public void responseBeingSentToOutputStream() throws IOException {
//        Channel channel = new HTTPChannel(fakeClient, requestParser, responseParser);
//        Response response = new MockResponseFactory().getValidResponse();
//        String expectedResult = "HTTP/1.1 200 OK\n" +
//                "Content-Type: application/json\n" +
//                "content-length: 24\n" +
//                "\n" +
//                dummyBody;
//        String sendResult = channel.send(response);
//        Assert.assertEquals(expectedResult, sendResult);
//    }
}
