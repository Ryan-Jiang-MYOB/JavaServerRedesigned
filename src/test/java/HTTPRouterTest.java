import Mocks.MockRequestFactory;
import Model.Enum.HTTPRequestType;
import Model.Request;
import Channel.HTTPRouter;
import Channel.Router;
import Server.Controller;
import Server.LogController;
import Server.SiteController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRouterTest {
    private Router router;
    private MockRequestFactory requestFactory;

    @Before
    public void setup () throws URISyntaxException {
        Map<URI, Controller> requestMap = new LinkedHashMap<>();
        requestMap.put(new URI("/log"), new LogController());
        requestMap.put(new URI("/site"), new SiteController());
        router = new HTTPRouter(requestMap);
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
        request = requestFactory.getValidRequest(HTTPRequestType.GET, requestFactory.validSiteURI);
        routingResult = router.routeToController(request);
        Assert.assertTrue(routingResult instanceof SiteController);
    }
}
