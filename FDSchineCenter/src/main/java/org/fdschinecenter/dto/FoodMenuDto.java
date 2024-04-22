package org.fdschinecenter.dto;

import java.io.Serializable;
/**
 * Class to manage FoodMenuDto.
 */
public record FoodMenuDto(int id,
                          String name,
                          String description,
                          double price,
                          String image,
                          RestaurantDto restaurant) implements Serializable {
}
