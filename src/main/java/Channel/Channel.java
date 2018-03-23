package Channel;

import Model.Request;
import Model.Response;

import java.io.IOException;

/**
 * Accepts a Socket connection, and handles input and output from/to that client.
 */
public interface Channel {
    Request fetch() throws IOException;
    boolean send(Response response);
}
