package org.fdschinecenter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * RestaurantAdmin entity class for table in database. It has a one-to-one relationship with Restaurant entity.
 */
@Entity
@Getter
@Setter
public class RestaurantAdmin extends User{

    @OneToOne
    private Restaurant manageRestaurant;
}
