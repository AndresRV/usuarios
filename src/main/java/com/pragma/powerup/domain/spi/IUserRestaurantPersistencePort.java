package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.union.UserRestaurant;

public interface IUserRestaurantPersistencePort {
    void saveUserRestaurant(UserRestaurant userRestaurant);
    UserRestaurant getUserRestaurantByIdUser(Long idUser);
}
