package Model;

import Model.Enum.ResponseStatus;

import java.util.Map;

public interface Response {
    ResponseStatus getStatus();

    String getProtocol();

    Map<String, String> getHeadersMap();

    void add_header(String key, String value);

    String getBody();
}
