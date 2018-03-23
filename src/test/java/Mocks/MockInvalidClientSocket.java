package Mocks;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockInvalidClientSocket extends Socket {
    @Override
    public InputStream getInputStream() throws IOException {
        throw new IOException("IOException -- Mocking IO exception from empty/closing socket");
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("IOException -- Mocking IO exception from empty/closing/invalid socket");
    }


}
