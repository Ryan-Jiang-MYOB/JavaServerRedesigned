package Service.LogService;

import Model.Enum.HTTPResponseStatus;
import Model.Request;
import Model.Response;
import Service.Controller;
import Worker.HTTPResponseParser;
import Worker.ResponseParser;

import java.util.Date;
import java.util.HashMap;

public class LogController implements Controller {
    ResponseParser responseParser;

    public LogController() {
        responseParser = new HTTPResponseParser();
    }

    @Override
    public Response doGet(Request request) {
        return responseParser.createResponse("HTTP/1.1",
                HTTPResponseStatus.OK,
                new HashMap<String, String>(),
                new Date().toString());
    }

    @Override
    public Response doPost(Request request) {
        return null;
    }
}
