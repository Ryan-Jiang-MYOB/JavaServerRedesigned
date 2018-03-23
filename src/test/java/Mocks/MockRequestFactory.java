package Mocks;

import Model.Enum.HTTPRequestType;
import Model.HTTPRequest;
import Model.Request;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MockRequestFactory {
    public final String validHTTPProtocol = "HTTP/1.1";
    public final URI validLogURI = new URI("/log");
    public final URI unavailableURI = new URI("/random");
    public final String validPostHeaderString = "Host: localhost:8888\n" +
            "Content-Type: application/json\n" +
            "Cache-Control: no-cache\n" +
            "content-length: 24\n" +
            "Postman-Token: 41a40579-8368-4f89-9b2b-b02c4475fafd\n" +
            "\n";
    public final String invalidPostHeaderStringWithWrongLength = "Host: localhost:8888\n" +
            "Content-Type: application/json\n" +
            "Cache-Control: no-cache\n" +
            "content-length: 96\n" +
            "Postman-Token: 41a40579-8368-4f89-9b2b-b02c4475fafd\n" +
            "\n";
    public final String validPostDummyBody = "{\"name\":\"ryan\",\"age\":25}";

    public MockRequestFactory() throws URISyntaxException {
    }

    public Request getValidRequest(HTTPRequestType type, URI passedURI) {
        Map<String, String> headersMap;
        switch (type) {
            case POST:
                headersMap = getValidPostHeader();
            case GET:
                headersMap = getValidGetHeader();
            default:
                headersMap = getValidGetHeader();
        }
        return new HTTPRequest(type, passedURI, validHTTPProtocol, headersMap, validPostDummyBody);
    }

    public Request getPostRequestWithUnavailableURI() {
        Map<String, String> headersMap = getValidPostHeader();
        return new HTTPRequest(HTTPRequestType.POST, unavailableURI, validHTTPProtocol, headersMap, validPostDummyBody);
    }

    private Map<String, String> getValidPostHeader() {
        Map<String, String> headersMap = new LinkedHashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("Cache-Control", "no-cache");
        headersMap.put("content-length", "24");
        headersMap.put("Postman-Token", "41a40579-8368-4f89-9b2b-b02c4475fafd");
        return headersMap;
    }

    private Map<String, String> getValidGetHeader() {
        Map<String, String> headersMap = new LinkedHashMap<>();
        headersMap.put("Cache-Control", "no-cache");
        headersMap.put("Postman-Token", "41a40579-8368-4f89-9b2b-b02c4475fafd");
        return headersMap;
    }
}
