package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRestaurantRequest;

public interface IUserRestaurantHandler {
    void saveUserRestaurant(UserRestaurantRequest userRestaurantRequest);
    Long getRestaurantByDocumentNumberUser(Integer documentNumber);
}
