package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No data found for the requested petition"),
    ROLE_FOUND("No role found for the requested petition"),
    USER_ALREADY_EXISTS("There is a user with the same document number"),
    USER_FOUND("No user found for the requested petition"),
    USER_RESTAURANT_FOUND("No restaurant found for the user requested"),
    USER_RESTAURANT_ALREADY_EXISTS("The user is already assigned to a restaurant");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}