package Mocks;

import Model.Enum.HTTPRequestType;
import Model.HTTPRequest;
import Model.Request;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MockRequestFactory {
    public final String validPostProtocol = "HTTP/1.1";
    public final URI validPostURI = new URI("/path");
    public final String validPostHeaders = "Host: localhost:8888\n" +
            "Content-Type: application/json\n" +
            "Cache-Control: no-cache\n" +
            "content-length: 24\n" +
            "Postman-Token: 41a40579-8368-4f89-9b2b-b02c4475fafd\n" +
            "\n";
    public final String validPostDummyBody = "{\"name\":\"ryan\",\"age\":25}";

    public MockRequestFactory() throws URISyntaxException {
    }

    public Request getValidPostRequest() {
        Map<String, String> headersMap = new LinkedHashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("Cache-Control", "no-cache");
        headersMap.put("content-length", "24");
        headersMap.put("Postman-Token", "41a40579-8368-4f89-9b2b-b02c4475fafd");
        return new HTTPRequest(HTTPRequestType.POST, validPostURI, validPostProtocol, headersMap, validPostDummyBody);
    }
}
