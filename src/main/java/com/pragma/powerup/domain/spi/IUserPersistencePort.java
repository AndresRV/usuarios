package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
    User findById(Long id);
    User findByDocumentNumber(Integer documentNumber);
}
