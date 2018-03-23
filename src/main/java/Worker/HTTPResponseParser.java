package Worker;

import Model.Enum.HTTPResponseStatus;
import Model.HTTPResponse;
import Model.Response;

import java.util.Map;

public class HTTPResponseParser implements ResponseParser {

    @Override
    public Response createResponse(String protocol, HTTPResponseStatus status, Map<String, String> headersMap, String body) {
        if (body.isEmpty()) {
            headersMap.remove("content-length");
        } else {
            int byteSize = body.getBytes().length;
            headersMap.put("content-length", String.valueOf(byteSize));
        }
        return new HTTPResponse(protocol, status, headersMap, body);
    }

    @Override
    public String parseResponseToString(Response response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parseResponseStatusLine(response));
        stringBuilder.append("\n");
        stringBuilder.append(parseResponseHeaders(response));
        stringBuilder.append("\n");
        stringBuilder.append(response.getBody());

        return stringBuilder.toString();
    }

    private String parseResponseStatusLine(Response response) {
        String statusLine = response.getProtocol() +
                " " +
                String.valueOf(response.getStatus().getStatusCode()) +
                " " +
                response.getStatus().getStatusText();
        return statusLine;
    }

    private String parseResponseHeaders(Response response) {
        StringBuilder headersString = new StringBuilder();
        Map<String, String> headersMap = response.getHeadersMap();
        for (String key:response.getHeadersMap().keySet()) {
            headersString.append(key).append(": ").append(headersMap.get(key)).append("\n");
        }
        return headersString.toString();
    }
}
