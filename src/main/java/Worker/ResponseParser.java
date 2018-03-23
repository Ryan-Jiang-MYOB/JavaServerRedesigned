package Worker;

import Model.Enum.HTTPResponseStatus;
import Model.Response;

import java.util.Map;

public interface ResponseParser {
    String parseResponseToString(Response response);
    Response createResponse(String protocol, HTTPResponseStatus status, Map<String, String> headersMap, String body);
}
