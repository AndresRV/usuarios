package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.union.UserRestaurant;

public interface IUserRestaurantServicePort {
    void saveUserRestaurant(UserRestaurant userRestaurant);
    Long getRestaurantByIdUser(Long idUser);
}
