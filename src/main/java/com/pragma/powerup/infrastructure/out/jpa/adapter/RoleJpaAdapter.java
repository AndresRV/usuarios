package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.infrastructure.exception.RoleNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleEntityMapper.toRole(roleRepository.findByRoleName(roleName)
                .orElseThrow(RoleNotFoundException::new));
    }

    @Override
    public Role findById(Long id) {
        return roleEntityMapper.toRole(roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new));
    }
}
