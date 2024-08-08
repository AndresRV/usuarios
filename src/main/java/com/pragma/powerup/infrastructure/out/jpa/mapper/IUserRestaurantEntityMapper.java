package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.union.UserRestaurant;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserRestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRestaurantEntityMapper {
    UserRestaurantEntity toEntity(UserRestaurant userRestaurant);
    UserRestaurant toUserRestaurant(UserRestaurantEntity userRestaurantEntity);
}
