package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.UserRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRestaurantRepository extends JpaRepository<UserRestaurantEntity, Long> {
    Optional<UserRestaurantEntity> findByIdUser(Long idUser);
}
