package Channel;

import Model.Request;
import Server.Controller;
import Server.LogController;

import java.net.URI;
import java.util.Map;

public class HTTPRouter implements Router {
    private Map<URI, Controller> requestMap;

    // Should I implement injection here?
    public HTTPRouter(Map<URI, Controller> requestMap) {
        this.requestMap = requestMap;
    }

    @Override
    public Controller routeToController(Request request) {
        return requestMap.get(request.getPath());
    }
}
