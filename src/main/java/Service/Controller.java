package Service;

import Model.Request;
import Model.Response;

public interface Controller {
    Response doGet(Request request);
    Response doPost(Request request);
}
