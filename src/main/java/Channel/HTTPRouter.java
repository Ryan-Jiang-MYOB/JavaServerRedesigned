package Channel;

import Model.Request;
import Server.Controller;
import Server.LogController;

public class HTTPRouter implements Router {
    @Override
    public Controller routeToController(Request request) {
        return new LogController()
    }
}
