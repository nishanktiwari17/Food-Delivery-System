package com.oviyahsridhar.fooddelivery.service;

import com.oviyahsridhar.fooddelivery.dto.RestaurantDto;
import com.oviyahsridhar.fooddelivery.response.RestaurantResponse;
import com.oviyahsridhar.fooddelivery.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    void saveRestaurant(Restaurant restaurant);
    RestaurantDto findRestaurantById(String id);
    RestaurantResponse getAllRestaurant(int pageNumber, int pageSize);
    List<RestaurantDto> findNearRestaurant(double latitude, double longitude, double radius);
}
