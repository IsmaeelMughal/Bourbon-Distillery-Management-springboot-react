package vumc.org.springreact.utils;


import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final Integer STATUS_SUCCESS = HttpStatus.OK.value();
    public static final Integer STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    public static final Integer STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.value();
    public static final Integer STATUS_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();
    public static final Integer STATUS_CREATED = HttpStatus.CREATED.value();
    public static final List<String> CORS_ALLOWED_ORIGINS = List.of("http://127.0.0.1:3000", "http://localhost:3000");
    public static final List<String> CORS_ALLOWED_HEADERS = Arrays.asList(
            "Origin", "Access-Control-Allow-Origin", "Content-Type",
            "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
            "Access-Control-Request-Method", "Access-Control-Request-Headers");
    public static final List<String> CORS_EXPOSED_HEADERS =Arrays.asList(
            "Origin", "Content-Type", "Accept", "Authorization",
            "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
    public static final List<String> CORS_ALLOWED_METHODS =Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");
    public static final String CORS_CONFIGURATION_PATTERN = "/**";
}
