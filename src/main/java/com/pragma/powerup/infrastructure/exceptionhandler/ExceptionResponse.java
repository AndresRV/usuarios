package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    ROLE_FOUND("No role found for the requested petition"),
    USER_ALREADY_EXISTS("There is a user with the same document number");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}