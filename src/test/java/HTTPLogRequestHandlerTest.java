import Mocks.MockRequestFactory;
import Model.Enum.HTTPRequestType;
import Model.Enum.HTTPResponseStatus;
import Model.Request;
import Model.Response;
import Service.RequestHandler;
import Service.LogService.LogRequestHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPLogRequestHandlerTest {
    RequestHandler logRequestHandler;
    MockRequestFactory requestFactory;

    @Before
    public void setup() throws URISyntaxException {
        logRequestHandler = new LogRequestHandler();
        requestFactory = new MockRequestFactory();
    }

    @Test
    public void doValidGetInControllerShouldNotReceiveNull() throws URISyntaxException {
        Response result = logRequestHandler.doGet(requestFactory.getValidRequest(HTTPRequestType.GET, new URI("/log")));
        Assert.assertNotNull(result);
    }

    @Test
    public void doValidGetRequestShouldReturnCorrectResponse() throws URISyntaxException {
        Request request = requestFactory.getValidRequest(HTTPRequestType.GET, new URI("/log"));
        Response response = logRequestHandler.handleRequest(request);
        Assert.assertEquals(HTTPResponseStatus.OK, response.getStatus());
    }

    @Test
    public void return400WhenRequestTypeIsNotSupported() throws URISyntaxException {
        Request request = requestFactory.getValidRequest(HTTPRequestType.HEAD, new URI("/log"));
        Response response = logRequestHandler.handleRequest(request);
        Assert.assertEquals(HTTPResponseStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    public void return400WhenRequestIsValidButMissingQueryOfID() {
        Request request = requestFactory.getGetRequestWithQuery(requestFactory.validLogURI, new LinkedHashMap<String, String>());
        Response response = logRequestHandler.handleRequest(request);
        System.out.println(response.getBody());
        Assert.assertEquals(HTTPResponseStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    public void return204WhenRequestIsValidWithQueryButNoLogMatchingID() {
        Map<String, String> queriesMap = new LinkedHashMap<>();
        queriesMap.put("id", "8888887");
        Request request = requestFactory.getGetRequestWithQuery(requestFactory.validLogURI, queriesMap);
        Response response = logRequestHandler.handleRequest(request);
        System.out.println(response.getBody());
        Assert.assertEquals(HTTPResponseStatus.OK, response.getStatus());
    }

    @Test
    public void return200WhenRequestIsValidWithQueryAndIDMatches() {
        Map<String, String> queriesMap = new LinkedHashMap<>();
        queriesMap.put("id", "12");
        Request request = requestFactory.getGetRequestWithQuery(requestFactory.validLogURI, queriesMap);
        Response response = logRequestHandler.handleRequest(request);
        System.out.println(response.getBody());
        Assert.assertEquals(HTTPResponseStatus.OK, response.getStatus());
    }

}
