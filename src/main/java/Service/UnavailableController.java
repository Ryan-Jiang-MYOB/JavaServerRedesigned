package Service;

import Model.Enum.HTTPResponseStatus;
import Model.Request;
import Model.Response;
import Worker.HTTPResponseParser;
import Worker.ResponseParser;

import java.util.Date;
import java.util.HashMap;

public class UnavailableController implements Controller {
    ResponseParser responseParser;

    public UnavailableController() {
        responseParser = new HTTPResponseParser();
    }
    @Override
    public Response doGet(Request request) {
        return responseParser.createResponse("HTTP/1.1",
                HTTPResponseStatus.NOT_FOUND,
                new HashMap<String, String>(),
                "Dude the stuff you requested is not there");
    }

    @Override
    public Response doPost(Request request) {
        return null;
    }
}
