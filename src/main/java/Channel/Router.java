package Channel;

import Model.Request;
import Service.Controller;

import java.net.URI;

public interface Router {
    Controller routeToController(Request request);
    void mapController(URI uri, Controller controller);
}
