package com.famtwen.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Uncategorized error"),
    USER_EXISTED(1002, "User existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters"),
    ;
    private int Code;
    private String message;

    ErrorCode(int code, String message) {
        Code = code;
        this.message = message;
    }

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return message;
    }
}