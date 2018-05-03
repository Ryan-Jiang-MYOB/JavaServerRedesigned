package Service.LogService;

import Model.Enum.HTTPRequestType;
import Model.Enum.HTTPResponseStatus;
import Model.Request;
import Model.Response;
import Service.RequestHandler;
import Worker.HTTPResponseParser;
import Worker.ResponseParser;

import java.util.HashMap;

public class LogRequestHandler implements RequestHandler {
    ResponseParser responseParser;

    public LogRequestHandler() {
        responseParser = new HTTPResponseParser();
    }

    @Override
    public Response handleRequest(Request request) {

        // Send error msg if request is not HTTP.
        if (!(request.getType() instanceof HTTPRequestType))
            return responseParser.createResponse("HTTP/1.1",
                    HTTPResponseStatus.BAD_REQUEST,
                    new HashMap<String, String>(),
                    "400 Bad Request: Request is NOT HTTP request, check your request type and try again.");

        // Direct to the right Service.
        Response result;
        switch ((HTTPRequestType)request.getType()) {
            case GET: result = this.doGet(request);
            break;
            case POST: result = this.doPost(request);
            break;
            default: result = responseParser.createResponse("HTTP/1.1",
                    HTTPResponseStatus.BAD_REQUEST,
                    new HashMap<String, String>(),
                    "400 Bad Request: This HTTP request type is NOT supported.");
            break;
        }
        return result;
    }

    /**
     * To get valid dive log, GET request must provide log id in the query.
     * @param request HTTP Request Been fired.
     * @return The Response for the API call.
     */
    @Override
    public Response doGet(Request request) {
        String id = request.getQuery("id");
        Response response;

        // When ID is not provided, return bad request.
        if (id == null || id.isEmpty()) {
            response = responseParser.createResponse("HTTP/1.1",
                    HTTPResponseStatus.BAD_REQUEST,
                    new HashMap<String, String>(),
                    "400 Bad Request: Need to provide id as query to GET log.");

        } else {
            GetLogService getLogService = new GetLogService();
            String logString = getLogService.getLog(id);

            if (logString == null) {
                response = responseParser.createResponse("HTTP/1.1",
                        HTTPResponseStatus.OK,
                        new HashMap<String, String>(),
                        "There's no log matching the given ID.");

            } else {
                response = responseParser.createResponse("HTTP/1.1",
                        HTTPResponseStatus.OK,
                        new HashMap<String, String>(),
                        logString);
            }
        }
        return response;
    }

    @Override
    public Response doPost(Request request) {
        return responseParser.createResponse("HTTP/1.1",
                HTTPResponseStatus.BAD_REQUEST,
                new HashMap<String, String>(),
                "400 Bad Request: This HTTP request type is NOT supported.");
    }

}
