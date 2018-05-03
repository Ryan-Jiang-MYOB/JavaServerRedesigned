package Service;

import Model.Enum.HTTPResponseStatus;
import Model.Request;
import Model.Response;
import Worker.HTTPResponseParser;
import Worker.ResponseParser;

import java.util.HashMap;
import java.util.Map;

public class OptionRequestHandler implements RequestHandler {
    private ResponseParser responseParser;
    private String allowedOptions;

    public OptionRequestHandler(String allowedOptions) {
        this.responseParser = new HTTPResponseParser();
        this.allowedOptions = allowedOptions;
    }

    @Override
    public Response handleRequest(Request request) {
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Allow", this.allowedOptions);
        return responseParser.createResponse("HTTP/1.1",
                HTTPResponseStatus.OK,
                headerMap,
                "Empty");
    }

    @Override
    public Response doGet(Request request) {
        return null;
    }

    @Override
    public Response doPost(Request request) {
        return null;
    }
}
