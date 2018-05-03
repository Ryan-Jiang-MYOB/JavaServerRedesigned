package Service;

import Model.Request;
import Model.Response;

public interface RequestHandler {
    Response handleRequest(Request request);
    Response doGet(Request request);
    Response doPost(Request request);
}
