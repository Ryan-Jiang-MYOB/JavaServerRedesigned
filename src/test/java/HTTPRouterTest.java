import Channel.HTTPRouter;
import Channel.Router;
import Mocks.MockRequestFactory;
import Model.Enum.HTTPRequestType;
import Model.Request;
import Service.EmptyOkResponseRequestHandler;
import Service.RequestHandler;
import Service.LogService.LogRequestHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class HTTPRouterTest {
    private Router router;
    private MockRequestFactory requestFactory;

    @Before
    public void setup () throws URISyntaxException {
        router = new HTTPRouter();
        router.mapHandler(new URI("/log"), new LogRequestHandler());
        requestFactory = new MockRequestFactory();
    }

    @Test
    public void routerFunctionShouldReturnNotNull() {
        Request request = requestFactory.getValidRequest(HTTPRequestType.POST, requestFactory.validLogURI);
        RequestHandler routingResult = router.routeToController(request);
        Assert.assertNotNull(routingResult);
    }

    @Test
    public void routerShouldReturnUnavailableControllerWhenNoServiceAvailable() {
        Request request = requestFactory.getPostRequestWithUnavailableURI();
        RequestHandler routingResult = router.routeToController(request);
        Assert.assertTrue(routingResult instanceof EmptyOkResponseRequestHandler);
    }

    @Test
    public void routerShouldReturnCorrectControllerWhenAvailable() {
        Request request = requestFactory.getValidRequest(HTTPRequestType.GET, requestFactory.validLogURI);
        RequestHandler routingResult = router.routeToController(request);
        Assert.assertTrue(routingResult instanceof LogRequestHandler);
    }
}
