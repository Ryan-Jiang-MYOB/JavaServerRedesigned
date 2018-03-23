package Mocks;

import Model.Enum.HTTPResponseStatus;
import Model.HTTPResponse;
import Model.Response;

import java.util.LinkedHashMap;
import java.util.Map;

public class MockResponseFactory {

    public final String validHTTPProtocol = "HTTP/1.1";
    public final HTTPResponseStatus validHTTPStatus = HTTPResponseStatus.OK;
    public final Map validHeadersMap = new LinkedHashMap();
    public final String validBody = "{\"name\":\"ryan\",\"age\":25}";

    public Response getValidResponse() {
        validHeadersMap.put("Content-Type", "application/json");
        validHeadersMap.put("content-length", "24");
        return new HTTPResponse(validHTTPProtocol, validHTTPStatus, validHeadersMap, validBody);
    }

    public String getValidResponseString() {
        String responseString = "HTTP/1.1" + " "
                + HTTPResponseStatus.OK.getStatusCode() + " "
                + HTTPResponseStatus.OK.getStatusText() + "\n"
                + "Date: Sun, 08 Feb 2018\n"
                + "Accept-Ranges: bytes\n"
                + "content-length: 24\n"
                + "Connection: close\n"
                + "Content-Type: text/html\n"
                + "\n"
                + validBody;
        return responseString;
    }
}
