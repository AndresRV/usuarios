package com.pragma.powerup.infrastructure.security.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"username, message", "jwt", "status"})
public class AuthResponse {
    String username;
    String message;
    String jwt;
    boolean status;
}
