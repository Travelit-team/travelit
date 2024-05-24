package com.back.travelit.common.exception;

public enum ErrorCode {

    LOCATION_CODES_NOT_EXISTS(500, "L_001", "지역코드를 찾을 수 없습니다");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
