package Model;

import Model.Enum.RequestType;

import java.net.URI;

public interface Request {
    RequestType getType();

    URI getPath();

    String getBody();

    String getHeader(String headerKey);
}
