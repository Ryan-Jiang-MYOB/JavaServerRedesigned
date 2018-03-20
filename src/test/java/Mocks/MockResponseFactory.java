package Mocks;

import Model.Enum.HTTPResponseStatus;
import Model.HTTPResponse;
import Model.IResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class MockResponseFactory {

    public final String validHTTPProtocol = "HTTP/1.1";
    public final HTTPResponseStatus validHTTPStatus = HTTPResponseStatus.OK;
    public final Map validHeadersMap = new LinkedHashMap();
    public final String validBody = "{\n" +
            "\t\"name\": \"ryan\",\n" +
            "\t\"age\": 25\n" +
            "}";

    public IResponse getValidResponse() {
        validHeadersMap.put("Content-Type", "application/json");
        validHeadersMap.put("content-length", "24");
        return new HTTPResponse(validHTTPProtocol, validHTTPStatus, validHeadersMap, validBody);
    }
}
