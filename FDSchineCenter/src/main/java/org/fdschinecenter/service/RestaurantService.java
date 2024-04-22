package org.fdschinecenter.service;

import org.fdschinecenter.dto.RestaurantDto;
import org.fdschinecenter.response.RestaurantResponse;
import org.fdschinecenter.entity.Restaurant;

import java.util.List;
/**
 * Interface to manage RestaurantService.
 */
public interface RestaurantService {

    void saveRestaurant(Restaurant restaurant);
    RestaurantDto findRestaurantById(String id);
    RestaurantResponse getAllRestaurant(int pageNumber, int pageSize);
    List<RestaurantDto> findNearRestaurant(double latitude, double longitude, double radius);
}
