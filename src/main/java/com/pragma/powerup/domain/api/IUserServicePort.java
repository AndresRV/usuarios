package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
    User getUser(Long id);
    User getUserByDocumentNumber(Integer documentNumber);
}
