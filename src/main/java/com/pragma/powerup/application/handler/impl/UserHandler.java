package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequest;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveUser(String roleName, UserRequest userRequest) {
        Role role = roleServicePort.getRoleByRoleName(roleName);
        User user = userRequestMapper.toUser(userRequest);
        user.setIdRole(role.getId());
        userServicePort.saveUser(user);
    }
}
