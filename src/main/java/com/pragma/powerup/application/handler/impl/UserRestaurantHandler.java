package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRestaurantRequest;
import com.pragma.powerup.application.handler.IUserRestaurantHandler;
import com.pragma.powerup.application.mapper.IUserRestaurantRequestMapper;
import com.pragma.powerup.domain.api.IUserRestaurantServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.model.union.UserRestaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRestaurantHandler implements IUserRestaurantHandler {
    private final IUserRestaurantServicePort userRestaurantServicePort;
    private final IUserServicePort userServicePort;
    private final IUserRestaurantRequestMapper userRestaurantRequestMapper;

    @Override
    public void saveUserRestaurant(UserRestaurantRequest userRestaurantRequest) {
        User user = userServicePort.getUserByDocumentNumber(userRestaurantRequest.getDocumentNumber());

        UserRestaurant userRestaurant = userRestaurantRequestMapper.toUserRestaurant(userRestaurantRequest);
        userRestaurant.setIdUser(user.getId());

        userRestaurantServicePort.saveUserRestaurant(userRestaurant);
    }

    @Override
    public Long getRestaurantByDocumentNumberUser(Integer documentNumber) {
        User user = userServicePort.getUserByDocumentNumber(documentNumber);
        return userRestaurantServicePort.getRestaurantByIdUser(user.getId());
    }
}
