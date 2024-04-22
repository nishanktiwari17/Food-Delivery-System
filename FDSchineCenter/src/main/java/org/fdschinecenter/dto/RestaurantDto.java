package org.fdschinecenter.dto;

import org.fdschinecenter.entity.Address;
import org.fdschinecenter.entity.FoodMenu;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
/**
 * Class to manage RestaurantDto.
 */
public record RestaurantDto(UUID id,
                            String name,
                            String categories,
                            String contact,
                            String image,
                            Address addresses,
                            Set<FoodMenu> foodMenuList,
                            RestaurantAdminDto restaurantAdminDto) implements Serializable {
}
