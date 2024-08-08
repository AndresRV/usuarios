package com.pragma.powerup.domain.model.union;

public class UserRestaurant {
    private Long idUser;
    private Long idRestaurant;

    public UserRestaurant(Long idUser, Long idRestaurant) {
        this.idUser = idUser;
        this.idRestaurant = idRestaurant;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
