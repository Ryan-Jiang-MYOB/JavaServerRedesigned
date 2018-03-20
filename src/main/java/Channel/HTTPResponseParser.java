package Channel;

import Model.IResponse;

import java.io.OutputStream;
import java.util.Map;

public class HTTPResponseParser implements ResponseParser {

    @Override
    public String parseResponse(IResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parseResponseStatusLine(response));
        stringBuilder.append("\n");
        stringBuilder.append(parseResponseHeaders(response));
        stringBuilder.append("\n");
        stringBuilder.append(response.get_body().replaceAll("\\s", ""));

        return stringBuilder.toString();
    }

    private String parseResponseStatusLine(IResponse response) {
        String statusLine = response.get_protocol() +
                " " +
                String.valueOf(response.get_status().getStatusCode()) +
                " " +
                response.get_status().getStatusText();
        return statusLine;
    }

    private String parseResponseHeaders(IResponse response) {
        StringBuilder headersString = new StringBuilder();
        Map<String, String> headersMap = response.get_headers();
        for (String key:response.get_headers().keySet()) {
            headersString.append(key).append(": ").append(headersMap.get(key)).append("\n");
        }
        return headersString.toString();
    }
}
