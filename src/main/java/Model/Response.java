package Model;

import Model.Enum.ResponseStatus;

import java.util.Map;

public interface Response {
    ResponseStatus get_status();

    String get_protocol();

    Map<String, String> get_headers();

    String get_body();
}