package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRestaurantRequest;
import com.pragma.powerup.application.handler.IUserRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userrestaurant")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class UserRestaurantRestController {
    private final IUserRestaurantHandler userRestaurantHandler;

    @PostMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> saveUserRestaurant(@RequestBody UserRestaurantRequest userRestaurantRequest) {
        userRestaurantHandler.saveUserRestaurant(userRestaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{documentNumber}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getRestaurantByUser(@PathVariable Integer documentNumber) {
        return ResponseEntity.ok(userRestaurantHandler.getRestaurantByDocumentNumberUser(documentNumber));
    }
}
