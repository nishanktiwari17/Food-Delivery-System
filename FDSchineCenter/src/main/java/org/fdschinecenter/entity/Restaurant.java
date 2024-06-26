package org.fdschinecenter.entity;

import org.fdschinecenter.annotations.NotEmptyNotBlank;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Restaurant entity class for table in database. It has a one-to-one relationship with Address entity and RestaurantAdmin entity.
 */
@Entity
@Getter
@Setter
@DynamicUpdate
@Table(indexes = {@Index(name = "idx_lat_lng", columnList = "latitude, longitude")})
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmptyNotBlank
    private String name;

    @NotEmptyNotBlank
    private String categories;

    @NotEmptyNotBlank
    private String contact;

    private String image;

    private Double latitude;
    private Double longitude;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Set<FoodMenu> foodMenuList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "manageRestaurant")
    private RestaurantAdmin restaurantAdmin;
}
