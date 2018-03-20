package Mocks;

import java.io.*;
import java.net.Socket;

public class MockClientSocket extends Socket {
    private String _input;

    public MockClientSocket(String requestLine, String headers, String body) {
        StringBuilder builder = new StringBuilder();
        builder.append(requestLine);
        builder.append(headers);
        builder.append(body);
        _input = builder.toString();
        System.out.println(_input);
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(_input.getBytes());
    }

    @Override
    public OutputStream getOutputStream() {
        return new ByteArrayOutputStream();
    }
}
