package Worker;

import Service.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRequestMapper implements RequestMapper{
    private Map<URI, Controller> requestMap;

    /**
     * These request mappings are now hardcoded.
     * Which means that this class is just a 'setting' config for requestMapping.
     * @throws URISyntaxException
     */
    public HTTPRequestMapper() {
        requestMap = new LinkedHashMap<>();
    }

    @Override
    public Map<URI, Controller> returnRequestMapping() {
        return requestMap;
    }

    @Override
    public void mapController(URI uri, Controller controller) {
        requestMap.put(uri, controller);
    }
}
