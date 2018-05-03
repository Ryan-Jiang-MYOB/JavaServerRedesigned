package Channel;

import Model.Request;
import Service.EmptyOkResponseRequestHandler;
import Service.OptionRequestHandler;
import Service.RequestHandler;
import Service.LogService.LogRequestHandler;
import Service.UnavailableRequestHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRouter implements Router {
    private Map<URI, RequestHandler> requestMap;

    public HTTPRouter() throws URISyntaxException {
        this.requestMap = new LinkedHashMap<>();

        // Request Mapping at instantiation, is this the right way?
        mapHandler(new URI("/log"), new LogRequestHandler());
        mapHandler(new URI("/foobar"), new UnavailableRequestHandler());
        mapHandler(new URI("/method_options"), new OptionRequestHandler("GET,HEAD,POST,OPTIONS,PUT"));
        mapHandler(new URI("/method_options2"), new OptionRequestHandler("GET,OPTIONS,HEAD"));
    }

    @Override
    public RequestHandler routeToController(Request request) {
        if (requestMap.containsKey(request.getPath())) {
            return requestMap.get(request.getPath());
        } else {
            return new EmptyOkResponseRequestHandler();
        }
    }

    @Override
    public void mapHandler(URI uri, RequestHandler requestHandler) {
        requestMap.put(uri, requestHandler);
    }
}
