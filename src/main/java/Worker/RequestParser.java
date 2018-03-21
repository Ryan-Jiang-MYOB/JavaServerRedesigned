package Worker;

import CustomException.InvalidRequestException;
import Model.Request;

import java.io.BufferedReader;
import java.io.IOException;

public interface RequestParser {
    Request parseRequest(BufferedReader reader) throws IOException, InvalidRequestException;
}
