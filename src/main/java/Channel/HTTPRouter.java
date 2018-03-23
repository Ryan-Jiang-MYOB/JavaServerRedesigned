package Channel;

import Model.Request;
import Service.Controller;
import Service.LogService.LogController;
import Service.UnavailableController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRouter implements Router {
    private Map<URI, Controller> requestMap;

    // Should I implement injection here?
    public HTTPRouter() throws URISyntaxException {
        this.requestMap = new LinkedHashMap<>();
        mapController(new URI("/log"), new LogController());
    }

    @Override
    public Controller routeToController(Request request) {
        if (requestMap.containsKey(request.getPath())) {
            return requestMap.get(request.getPath());
        } else {
            return new UnavailableController();
        }
    }

    @Override
    public void mapController(URI uri, Controller controller) {
        requestMap.put(uri, controller);
    }
}
