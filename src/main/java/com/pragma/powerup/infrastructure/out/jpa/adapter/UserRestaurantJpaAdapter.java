package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.union.UserRestaurant;
import com.pragma.powerup.domain.spi.IUserRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserRestaurantAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.UserRestaurantNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserRestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRestaurantJpaAdapter implements IUserRestaurantPersistencePort {
    private final IUserRestaurantRepository userRestaurantRepository;
    private final IUserRestaurantEntityMapper userRestaurantEntityMapper;

    @Override
    public void saveUserRestaurant(UserRestaurant userRestaurant) {
        if (userRestaurantRepository.findByIdUser(userRestaurant.getIdUser()).isPresent()) {
            throw new UserRestaurantAlreadyExistsException();
        }

        userRestaurantRepository.save(userRestaurantEntityMapper.toEntity(userRestaurant));
    }

    @Override
    public UserRestaurant getUserRestaurantByIdUser(Long idUser) {
        UserRestaurantEntity userRestaurantEntity =  userRestaurantRepository.findByIdUser(idUser)
                .orElseThrow(UserRestaurantNotFoundException::new);

        return userRestaurantEntityMapper.toUserRestaurant(userRestaurantEntity);
    }
}
