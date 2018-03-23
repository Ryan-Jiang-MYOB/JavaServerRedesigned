package Model.Enum;

public enum HTTPResponseStatus implements ResponseStatus {
    OK (200, "OK"),
    BAD_REQUEST (400, "Bad Request"),
    NOT_FOUND (404, "Not Found"),
    INTERNAL_SERVER_ERROR (500, "Internal Service Error"),
    SERVICE_UNAVAILAVLE (503, "Service Unavailable"),
    ;

    private final int statusCode;
    private final String statusText;

    HTTPResponseStatus(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }
}
