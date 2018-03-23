import Channel.*;
import Mocks.*;
import Model.Enum.HTTPRequestType;
import Model.HTTPRequest;
import Model.Request;
import Worker.HTTPRequestParser;
import Worker.HTTPResponseParser;
import Worker.RequestParser;
import Worker.ResponseParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;

public class HTTPChannelTest {
    Socket fakeClient;
    RequestParser requestParser;
    ResponseParser responseParser;
    String dummyBody;

    @Before
    public void setupClient() throws URISyntaxException {
        MockRequestFactory requestFactory = new MockRequestFactory();
        String requestLine = HTTPRequestType.POST.getRequestTypeText() + " "
                + requestFactory.validLogURI.toString() + " "
                + requestFactory.validHTTPProtocol + "\n";
        String headers = requestFactory.validPostHeaderString;
        dummyBody = requestFactory.validPostDummyBody;

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

    @Test
    public void sendingResponseToValidSocketShouldReturnTrue() {
        MockSpyValidResponseParser mockSpyValidResponseParser = new MockSpyValidResponseParser();
        MockResponseFactory responseFactory = new MockResponseFactory();
        Channel channel = new HTTPChannel(fakeClient, requestParser, mockSpyValidResponseParser);
        boolean result = channel.send(responseFactory.getValidResponse());
        Assert.assertTrue(result);
        Assert.assertEquals(1, mockSpyValidResponseParser.parseResponseCalled);
    }

    @Test
    public void sendingResponseToInvalidSocketShouldReturnFalse() {
        MockSpyValidResponseParser mockSpyValidResponseParser = new MockSpyValidResponseParser();
        MockResponseFactory responseFactory = new MockResponseFactory();
        Channel channel = new HTTPChannel(new MockInvalidClientSocket(), requestParser, mockSpyValidResponseParser);
        boolean result = channel.send(responseFactory.getValidResponse());
        Assert.assertFalse(result);
        Assert.assertEquals(1, mockSpyValidResponseParser.parseResponseCalled);
    }
}
