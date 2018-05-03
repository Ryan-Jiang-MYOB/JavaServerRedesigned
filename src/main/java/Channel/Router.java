package Channel;

import Model.Request;
import Service.RequestHandler;

import java.net.URI;

public interface Router {
    RequestHandler routeToController(Request request);
    void mapHandler(URI uri, RequestHandler requestHandler);
}
