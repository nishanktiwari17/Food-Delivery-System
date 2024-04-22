package org.fdschinecenter.dto;

/**
 * Class to manage CustomerOrderDto.
 */
public record CustomerOrderDto(int orderId,
                               int foodId,
                               String name,
                               String description,
                               Double price,
                               String restaurantId,
                               UserDto userDto){
}
