package Model;

import Model.Enum.HTTPResponseStatus;
import Model.Enum.ResponseStatus;

import java.util.Map;

public class HTTPResponse implements Response {
    private String protocol;
    private HTTPResponseStatus status;
    private Map<String, String> headersMap;
    private String body;

    public HTTPResponse(String protocol, HTTPResponseStatus status, Map<String, String> headersMap, String body) {
        this.protocol = protocol;
        this.status = status;
        this.headersMap = headersMap;
        this.body = body;
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public Map<String, String> getHeadersMap() {
        return headersMap;
    }

    @Override
    public void add_header(String key, String value) {
        headersMap.put(key, value);
    }

    @Override
    public String getBody() {
        return body;
    }
}
