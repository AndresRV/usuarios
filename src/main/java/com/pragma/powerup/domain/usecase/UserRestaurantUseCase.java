package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserRestaurantServicePort;
import com.pragma.powerup.domain.model.union.UserRestaurant;
import com.pragma.powerup.domain.spi.IUserRestaurantPersistencePort;

public class UserRestaurantUseCase implements IUserRestaurantServicePort {
    private final IUserRestaurantPersistencePort userRestaurantPersistencePort;

    public UserRestaurantUseCase(IUserRestaurantPersistencePort userRestaurantPersistencePort) {
        this.userRestaurantPersistencePort = userRestaurantPersistencePort;
    }

    @Override
    public void saveUserRestaurant(UserRestaurant userRestaurant) {
        userRestaurantPersistencePort.saveUserRestaurant(userRestaurant);
    }

    @Override
    public Long getRestaurantByIdUser(Long idUser) {
        return userRestaurantPersistencePort.getUserRestaurantByIdUser(idUser).getIdRestaurant();
    }
}
