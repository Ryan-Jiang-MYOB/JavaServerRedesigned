package Worker;

import Service.Controller;

import java.net.URI;
import java.util.Map;

public interface RequestMapper {
    Map<URI, Controller> returnRequestMapping();
    void mapController(URI uri, Controller controller);
}
