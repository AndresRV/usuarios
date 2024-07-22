package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.infrastructure.security.dto.AuthLoginRequest;
import com.pragma.powerup.infrastructure.security.dto.AuthResponse;
import com.pragma.powerup.infrastructure.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
public class AuthenticationController {

    //TODO: MEJORA se conecta directamente a seguridad pero podria estar entrando el handler
    //inytecta clase directactemente en vez de interfaz??
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}
