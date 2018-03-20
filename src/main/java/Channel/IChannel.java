package Channel;

import CustomException.InvalidRequestException;
import Model.IRequest;
import Model.IResponse;

import java.io.IOException;

/**
 * Accepts a Socket connection, and handles input and output from/to that client.
 */
public interface IChannel {
    IRequest fetch() throws IOException;
    String send(IResponse response) throws IOException;
}
