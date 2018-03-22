package Mocks;

import CustomException.InvalidRequestException;
import Model.Request;
import Worker.HTTPRequestParser;

import java.io.BufferedReader;
import java.io.IOException;

public class MockSpyInvalidRequestParser extends HTTPRequestParser {
    public int parseRequestCalled = 0;
    @Override
    public Request parseRequest(BufferedReader reader) throws InvalidRequestException {
        parseRequestCalled ++;
        throw new InvalidRequestException("Invalid Request - Mocking invalid request");
    }
}
