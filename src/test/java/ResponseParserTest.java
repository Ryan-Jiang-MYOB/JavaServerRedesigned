import Model.Response;
import Worker.HTTPResponseParser;
import Worker.ResponseParser;
import Mocks.MockResponseFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class ResponseParserTest {
    ResponseParser parser;
    MockResponseFactory factory;

    @Before
    public void setup() {
        parser = new HTTPResponseParser();
        factory = new MockResponseFactory();
    }

    @Test
    public void parseResponseShouldReturnString() {
        Response response = factory.getValidResponse();
        String responseString = parser.parseResponse(response);
        Assert.assertNotNull(responseString);
    }

    @Test
    public void parseResponseShouldReturnCorrectStatus() {
        Response response = factory.getValidResponse();
        String responseString = parser.parseResponse(response);
        Scanner responseScanner = new Scanner(responseString);
        String statusLine = responseScanner.nextLine();
        String[] statusFields = statusLine.split(" ");
        Assert.assertEquals("HTTP/1.1", statusFields[0]);
        Assert.assertEquals("200", statusFields[1]);
        Assert.assertEquals("OK", statusFields[2]);
    }

    @Test
    public void parseResponseShouldReturnCorrectHeaders() {
        Response response = factory.getValidResponse();
        String responseString = parser.parseResponse(response);
        Scanner responseScanner = new Scanner(responseString);
        responseScanner.nextLine();
        String[] headerLine_1 = responseScanner.nextLine().split(": ");
        String[] headerLine_2 = responseScanner.nextLine().split(": ");

        // The order of insertion needs to be preserved by using LinkedHashMap in the Object.
        Assert.assertEquals("Content-Type", headerLine_1[0]);
        Assert.assertEquals("application/json", headerLine_1[1]);
        Assert.assertEquals("content-length", headerLine_2[0]);
        Assert.assertEquals("24", headerLine_2[1]);
    }

    @Test
    public void parseResponseShouldReturnCorrectBody() {
        Response response = factory.getValidResponse();
        String responseString = parser.parseResponse(response);
        String[] responseSplitLines = responseString.split("\n");
        int blankLineIndex = 0;
        for(int i = 0; i < responseSplitLines.length; i ++) {
            if (responseSplitLines[i].equals(""))
                blankLineIndex = i;
        }
        String bodyString = "";
        for(int j = blankLineIndex+1; j < responseSplitLines.length; j++) {
            bodyString += responseSplitLines[j];
        }
        Assert.assertEquals(factory.validBody.replaceAll("\\s", ""), bodyString);
    }
}
