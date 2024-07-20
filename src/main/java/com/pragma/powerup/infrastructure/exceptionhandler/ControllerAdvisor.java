package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.InvalidAdultAge;
import com.pragma.powerup.domain.exception.InvalidEmailException;
import com.pragma.powerup.domain.exception.InvalidPhoneNumber;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.RoleNotFoundException;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException ignoredUserAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            RoleNotFoundException ignoredRoleNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ROLE_FOUND.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException ignoredUserNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(
            InvalidEmailException invalidEmailException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidEmailException.getMessage()));
    }

    @ExceptionHandler(InvalidPhoneNumber.class)
    public ResponseEntity<Map<String, String>> handleInvalidPhoneNumber(
            InvalidPhoneNumber invalidPhoneNumber) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidPhoneNumber.getMessage()));
    }

    @ExceptionHandler(InvalidAdultAge.class)
    public ResponseEntity<Map<String, String>> handleInvalidAdultAge(
            InvalidAdultAge invalidAdultAge) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidAdultAge.getMessage()));
    }
}