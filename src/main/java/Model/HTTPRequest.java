package Model;

import Model.Enum.RequestType;

import java.net.URI;
import java.util.Map;

public class HTTPRequest implements IRequest {
    private RequestType type;
    private URI path;
    private String protocol;
    private Map<String, String> headers;
    private String body;

    public HTTPRequest(RequestType type, URI path, String protocol, Map<String, String> headers, String body) {
        this.type = type;
        this.path = path;
        this.protocol = protocol;
        this.headers = headers;
        this.body = body;
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
