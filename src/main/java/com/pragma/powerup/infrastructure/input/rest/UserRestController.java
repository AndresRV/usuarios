package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequest;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.infrastructure.security.UserDetailsServiceImpl;
import com.pragma.powerup.infrastructure.security.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    //TODO: MEJORA se conecta directamente a seguridad pero podria estar entrando el handler
    //inytecta clase directactemente en vez de interfaz??
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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

    @PostMapping("/Empleado")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> saveEmployeeUser(@RequestBody UserRequest userRequest) {
        userHandler.saveUser("Empleado", userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/Cliente")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> saveClient(@RequestBody UserRequest userRequest){
        AuthResponse tokenSaveUser = userDetailsService.saveUser("Cliente", userRequest);
        return new ResponseEntity<>(tokenSaveUser, HttpStatus.CREATED);
    }

    @GetMapping("/{idUser}/email")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> emailUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(userHandler.emailUser(idUser));
    }
}
