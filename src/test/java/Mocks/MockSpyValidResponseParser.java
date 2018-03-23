package Mocks;

import Model.Response;
import Worker.HTTPResponseParser;

public class MockSpyValidResponseParser extends HTTPResponseParser {
    public int parseResponseCalled = 0;

    @Override
    public String parseResponseToString(Response response) {
        parseResponseCalled ++;
        MockResponseFactory responseFactory = new MockResponseFactory();
        return responseFactory.getValidResponseString();
    }
}
