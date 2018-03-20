package Channel;

import CustomException.InvalidRequestException;
import Model.IRequest;

import java.io.BufferedReader;
import java.io.IOException;

public interface RequestParser {
    IRequest parseRequest(BufferedReader reader) throws IOException, InvalidRequestException;
}
