package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequest;

public interface IUserHandler {
    void saveUser(String roleName, UserRequest userRequest);
    boolean isOwnerUser(Long idUser);
    String emailUser(Long idUser);
}
