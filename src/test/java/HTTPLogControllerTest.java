import Mocks.MockRequestFactory;
import Model.Enum.HTTPRequestType;
import Model.Response;
import Service.Controller;
import Service.LogService.LogController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class HTTPLogControllerTest {
    Controller logController;
    MockRequestFactory requestFactory;

    @Before
    public void setup() throws URISyntaxException {
        logController = new LogController();
        requestFactory = new MockRequestFactory();
    }

    @Test
    public void doValidGetInControllerShouldNotReceiveNull() throws URISyntaxException {
        Response result = logController.doGet(requestFactory.getValidRequest(HTTPRequestType.GET, new URI("/log")));
        Assert.assertNotNull(result);
    }

}
