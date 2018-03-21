package Worker;

import Model.Response;

import java.util.Map;

public class HTTPResponseParser implements ResponseParser {

    @Override
    public String parseResponse(Response response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parseResponseStatusLine(response));
        stringBuilder.append("\n");
        stringBuilder.append(parseResponseHeaders(response));
        stringBuilder.append("\n");
        stringBuilder.append(response.get_body().replaceAll("\\s", ""));

        return stringBuilder.toString();
    }

    private String parseResponseStatusLine(Response response) {
        String statusLine = response.get_protocol() +
                " " +
                String.valueOf(response.get_status().getStatusCode()) +
                " " +
                response.get_status().getStatusText();
        return statusLine;
    }

    private String parseResponseHeaders(Response response) {
        StringBuilder headersString = new StringBuilder();
        Map<String, String> headersMap = response.get_headers();
        for (String key:response.get_headers().keySet()) {
            headersString.append(key).append(": ").append(headersMap.get(key)).append("\n");
        }
        return headersString.toString();
    }
}
