package Mocks;

import Model.Enum.HTTPRequestType;
import Model.Enum.RequestType;
import Model.Request;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ValidHTTPRequest implements Request {
    private RequestType type;
    private URI path;
    private String protocol;
    private Map<String, String> headers;
    private String body;

    public ValidHTTPRequest() {
        type = HTTPRequestType.GET;
        path = URI.create("/");
        protocol = "HTTP/1.1";
        headers = new HashMap<String, String>();
        body = "{ \"name\": \"Ryan\"}";
    }

    public RequestType getType() {
        return type;
    }

    public URI getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }
}
