import Mocks.MockRequestFactory;
import Model.Enum.HTTPRequestType;
import Model.Request;
import Channel.HTTPRouter;
import Channel.Router;
import Service.Controller;
import Service.LogService.LogController;
import Worker.HTTPRequestMapper;
import Worker.RequestMapper;
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
        router.mapController(new URI("/log"), new LogController());
        requestFactory = new MockRequestFactory();
    }

    @Test
    public void routerFunctionShouldReturnNotNull() {
        Request request = requestFactory.getValidRequest(HTTPRequestType.POST, requestFactory.validLogURI);
        Controller routingResult = router.routeToController(request);
        Assert.assertNotNull(routingResult);
    }

    @Test
    public void routerShouldReturnNullWhenNoServiceAvailable() {
        Request request = requestFactory.getPostRequestWithUnavailableURI();
        Controller routingResult = router.routeToController(request);
        Assert.assertNull(routingResult);
    }

    @Test
    public void routerShouldReturnCorrectControllerWhenAvailable() {
        Request request = requestFactory.getValidRequest(HTTPRequestType.GET, requestFactory.validLogURI);
        Controller routingResult = router.routeToController(request);
        Assert.assertTrue(routingResult instanceof LogController);
    }
}
