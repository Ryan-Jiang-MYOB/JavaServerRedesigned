package Mocks;

import CustomException.InvalidRequestException;
import Model.Enum.HTTPRequestType;
import Model.Request;
import Worker.HTTPRequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MockSpyValidRequestParser extends HTTPRequestParser {
    public int parseRequestCalled = 0;

    @Override
    public Request parseRequest(BufferedReader reader) throws IOException, InvalidRequestException{
        parseRequestCalled ++;
        MockRequestFactory requestFactory = null;
        try {
            requestFactory = new MockRequestFactory();
            return requestFactory.getValidRequest(HTTPRequestType.GET, new URI("/log"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
