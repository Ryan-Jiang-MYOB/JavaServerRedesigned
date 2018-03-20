import Channel.*;
import CustomException.InvalidRequestException;
import Mocks.MockClientSocket;
import Mocks.MockResponseFactory;
import Model.Enum.HTTPRequestType;
import Model.IRequest;
import Model.IResponse;
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
    public void fetchingDummyRequestFromClientSocket() throws IOException, InvalidRequestException {
        IChannel iChannel = new HTTPChannel(fakeClient, requestParser, responseParser);
        IRequest request = iChannel.fetch();
        Assert.assertNotNull(request);
    }

    @Test
    public void fetchingValidRequestFromChannel() throws IOException, InvalidRequestException {
        IChannel iChannel = new HTTPChannel(fakeClient, requestParser, responseParser);
        IRequest request = iChannel.fetch();
        Assert.assertEquals(HTTPRequestType.POST, request.getType());
        Assert.assertEquals(dummyBody, request.getBody());
    }

    @Test
    public void responseBeingSentToOutputStream() throws IOException {
        IChannel iChannel = new HTTPChannel(fakeClient, requestParser, responseParser);
        IResponse response = new MockResponseFactory().getValidResponse();
        String expectedResult = "HTTP/1.1 200 OK\n" +
                "Content-Type: application/json\n" +
                "content-length: 24\n" +
                "\n" +
                dummyBody;
        String sendResult = iChannel.send(response);
        Assert.assertEquals(expectedResult, sendResult);
    }
}
