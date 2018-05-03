package Model.Enum;

public enum HTTPRequestType implements RequestType {
    GET ("GET"),
    POST ("POST"),
    PUT ("PUT"),
    DELETE ("DELETE"),
    HEAD ("HEAD"),
    OPTIONS ("OPTIONS")
    ;

    private final String requestCode;

    HTTPRequestType(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getRequestTypeText() {
        return requestCode;
    }
}
