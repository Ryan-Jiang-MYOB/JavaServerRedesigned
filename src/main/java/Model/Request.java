package Model;

import Model.Enum.RequestType;

import java.net.URI;
import java.util.Map;

public interface Request {
    RequestType getType();

    URI getPath();

    String getBody();

    String getHeader(String headerKey);

    String getQuery(String key);
}
