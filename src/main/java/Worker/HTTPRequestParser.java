package Worker;

import CustomException.InvalidRequestException;
import CustomException.UnknownRequestTypeException;
import Model.Enum.HTTPRequestType;
import Model.Enum.RequestType;
import Model.HTTPRequest;
import Model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public class HTTPRequestParser implements RequestParser {

    public static final int REQUEST_LINE_LENGTH = 3;
    public static final String CONTENT_LENGTH = "content-length";

    @Override
    public Request parseRequest(BufferedReader reader) throws IOException, InvalidRequestException {
        if (reader.ready()) {
            String [] requestFields = readRequestLine(reader, REQUEST_LINE_LENGTH);

            RequestType requestType = parseRequestType(requestFields[0]);
            URI requestURI = parseRequestURI(requestFields[1]);
            Map<String, String> headers = readRequestHeaders(reader);
            String body = readRequestBody(reader, headers);

            return new HTTPRequest(requestType, requestURI, requestFields[2], headers, body);
        } else {
            throw new InvalidRequestException("Empty InputStream");
        }
    }

    private RequestType parseRequestType(String rawType) throws UnknownRequestTypeException {
        for (HTTPRequestType type: HTTPRequestType.values()) {
            if (type.getRequestTypeText().equals(rawType)) {
                return type;
            }
        }
        throw new UnknownRequestTypeException("Unknown Request Type - Not matching the HTTP types this server can process");
    }

    private URI parseRequestURI(String path) throws InvalidRequestException {
        try {
            return URI.create(path);
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidRequestException("Invalid Request Line - Incorrect reqeust URI.");
        }
    }

    // Should I expose to public to be able to test them??? -- No, if don't need it elsewhere, you leave it private.
    private String[] readRequestLine(BufferedReader reader, int lineLength) throws InvalidRequestException, IOException {
        String[] requestFields = reader.readLine().split(" ");
        if (requestFields.length != lineLength)
            throw new InvalidRequestException("Invalid Request Line - Expecting: " + lineLength + " but got " + requestFields);
        for (String field:requestFields) {
            if (field.isEmpty())
                throw new InvalidRequestException("Invalid Request Line - Empty field detected.");
        }
        return requestFields;
    }

    private Map<String, String> readRequestHeaders(BufferedReader reader) throws InvalidRequestException, IOException {
        String line;
        Map<String, String> headers = new LinkedHashMap<>();
        line = reader.readLine();
        while (!line.isEmpty()) {
            String[] headerTuple = line.split(": ", 2);
            if (headerTuple.length == 2) {
                headers.put(headerTuple[0], headerTuple[1]);
            } else {
                throw new InvalidRequestException("Invalid Request Header - Invalid header property reached.");
            }
            line = reader.readLine();
        }
        return headers;
    }

    private String readRequestBody(BufferedReader reader, Map<String, String> headers) throws InvalidRequestException {
        StringBuilder stringBuilder = new StringBuilder();
        if (headers.containsKey(CONTENT_LENGTH)) {
            try {
                int bodyLength = Integer.parseInt(headers.get(CONTENT_LENGTH));
                char[] charArray = new char[bodyLength];
                reader.read(charArray, 0, bodyLength);
                stringBuilder.append(charArray);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new InvalidRequestException("Invalid Request Header - No content-length provided");
            } catch (IOException e) {
                e.printStackTrace();
                throw new InvalidRequestException("Invalid Request Header - Incorrect content-length provided");
            }
            return stringBuilder.toString().replaceAll("\\s", "");
        } else {
            return  "";
        }
    }
}
