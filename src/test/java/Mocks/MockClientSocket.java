package Mocks;

import java.io.*;
import java.net.Socket;

public class MockClientSocket extends Socket {
    private String _input;
    public int getInputStreamCalled = 0;
    public int getOutputStreamCalled = 0;

    public MockClientSocket(String requestLine, String headers, String body) {
        StringBuilder builder = new StringBuilder();
        builder.append(requestLine);
        builder.append(headers);
        builder.append(body);
        _input = builder.toString();
    }

    @Override
    public InputStream getInputStream() {
        getInputStreamCalled ++;
        return new ByteArrayInputStream(_input.getBytes());
    }

    @Override
    public OutputStream getOutputStream() {
        getOutputStreamCalled ++;
        return new ByteArrayOutputStream();
    }
}
