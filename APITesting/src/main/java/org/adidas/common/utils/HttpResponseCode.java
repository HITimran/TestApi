package org.adidas.common.utils;

public enum HttpResponseCode {
    OK(200), CREATED(201), NO_CONTENT(204), NOT_MODIFIED(304),
    BAD_REQUEST(400), UNAUTHORIZED(401), FORBIDDEN(403),
    NOT_FOUND(404), CONFLICT(409), INTERNAL_SERVER_ERROR(500),
    ACCOUNT_BLOCKED(429);
    int getCode;

    HttpResponseCode(int code) {
        this.getCode = code;
    }

    public int getCode() {
        return getCode;
    }
}
