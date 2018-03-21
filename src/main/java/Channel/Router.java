package Channel;

import Model.Request;
import Server.Controller;

public interface Router {
    Controller routeToController(Request request);
}
