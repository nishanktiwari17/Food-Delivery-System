package org.fdschinecenter.response;

import org.fdschinecenter.dto.RestaurantDto;

import java.io.Serializable;
import java.util.List;
/**
 * Class to manage RestaurantResponse.
 */
public record RestaurantResponse(
        List<RestaurantDto> restaurantDtoList,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean lastPage) implements Serializable {
}
