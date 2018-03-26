package Model;

import Model.Enum.RequestType;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRequest implements Request {
    private RequestType type;
    private URI path;
    private String protocol;
    private Map<String, String> headersMap;
    private Map<String, String> queriesMap; // Optional, only needed for GET request.
    private String body;

    public HTTPRequest(RequestType type, URI path, String protocol, Map<String, String> headersMap, String body, Map<String, String> queriesMap) {
        this.type = type;
        this.path = path;
        this.protocol = protocol;
        this.headersMap = headersMap;
        this.body = body;
        this.queriesMap = queriesMap;
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
        return headersMap.get(headerKey);
    }

    @Override
    public String getQuery(String key) {
        return queriesMap.get(key);
    }
}
