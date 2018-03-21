package Model;

import Model.Enum.HTTPResponseStatus;
import Model.Enum.ResponseStatus;

import java.util.Map;

public class HTTPResponse implements Response {
    private String _protocol;
    private HTTPResponseStatus _status;
    private Map<String, String> _headers;
    private String _body;

    public HTTPResponse(String _protocol, HTTPResponseStatus _status, Map<String, String> _headers, String _body) {
        this._protocol = _protocol;
        this._status = _status;
        this._headers = _headers;
        this._body = _body;
    }

    public String get_protocol() {
        return _protocol;
    }

    public ResponseStatus get_status() {
        return _status;
    }

    public Map<String, String> get_headers() {
        return _headers;
    }

    public String get_body() {
        return _body;
    }
}
