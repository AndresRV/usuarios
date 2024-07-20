package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return rolePersistencePort.getRoleByRoleName(roleName);
    }

    @Override
    public boolean isOwnerRol(Long id) {
        Role role = rolePersistencePort.findById(id);
        return role.getRoleName().equals("Propietario");
    }
}
