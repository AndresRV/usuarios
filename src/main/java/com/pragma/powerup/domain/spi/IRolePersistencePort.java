package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Role;

public interface IRolePersistencePort {
    Role getRoleByRoleName(String roleName);
    Role findById(Long id);
}
