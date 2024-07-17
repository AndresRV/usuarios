package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    void getRoleByRoleName() {
        Role role = new Role(1L, "xx", "xxx");
        when(rolePersistencePort.getRoleByRoleName(anyString())).thenReturn(role);

        Role result = roleUseCase.getRoleByRoleName(anyString());

        Assertions.assertNotNull(result);
    }
}