package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequest;
import com.pragma.powerup.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping("/Propietario")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> saveUser(@RequestBody UserRequest userRequest) {
        userHandler.saveUser("Propietario", userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/ownerUser/{idUser}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Boolean> isOwnerUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(userHandler.isOwnerUser(idUser));
    }
}
