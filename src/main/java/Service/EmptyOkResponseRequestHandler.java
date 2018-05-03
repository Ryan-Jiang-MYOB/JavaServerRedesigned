package Service;

import Model.Enum.HTTPResponseStatus;
import Model.Request;
import Model.Response;
import Worker.HTTPResponseParser;
import Worker.ResponseParser;

import java.util.HashMap;

public class EmptyOkResponseRequestHandler implements RequestHandler {

    private ResponseParser responseParser;

    public EmptyOkResponseRequestHandler() {
        responseParser = new HTTPResponseParser();
    }

    @Override
    public Response handleRequest(Request request) {
        return responseParser.createResponse("HTTP/1.1",
                HTTPResponseStatus.OK,
                new HashMap<String, String>(),
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
