package Model;

import Model.Enum.RequestType;

import java.net.URI;

public interface IRequest {
    RequestType getType();

    URI getPath();

    String getBody();

    String getHeader(String headerKey);
}
