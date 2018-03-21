import Mocks.MockRequestFactory;
import Model.Request;
import Channel.HTTPRouter;
import Channel.Router;
import Server.Controller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

public class HTTPRouterTest {
    private Router router;
    private MockRequestFactory requestFactory;

    @Before
    public void setup () throws URISyntaxException {
        router = new HTTPRouter();
        requestFactory = new MockRequestFactory();
    }

    @Test
    public void routerFunctionShouldReturnNotNull() {
        Request request = requestFactory.getValidPostRequest();
        Controller routingResult = router.routeToController(request);
        Assert.assertNotNull(routingResult);
    }
}
