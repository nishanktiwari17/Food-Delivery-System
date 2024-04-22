package org.fdschinecenter.utils;

import org.fdschinecenter.dto.FoodMenuDto;
import org.fdschinecenter.dto.RestaurantAdminDto;
import org.fdschinecenter.dto.RestaurantDto;
import org.fdschinecenter.dto.UserDto;
import org.fdschinecenter.entity.*;
import org.fdschinecenter.request.OrderRequest;
import org.fdschinecenter.dto.CustomerOrderDto;

import java.util.*;
/**
 * Class to manage ModelMapper.
 */
public class ModelMapper {

    public static RestaurantDto restaurantToDto(Restaurant restaurant){

        // Converting to DTO
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCategories(),
                restaurant.getContact(),
                restaurant.getImage(),
                restaurant.getAddress(),
                restaurant.getFoodMenuList(),
                restaurantAdminToDto(restaurant.getRestaurantAdmin()));
    }
    // Add the userToDto method
    public static UserDto userToDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.isEnable(),
                user.getRole());
    }
    // Add the restaurantAdminToDto method
    public static RestaurantAdminDto restaurantAdminToDto(RestaurantAdmin restaurantAdmin){
        Optional<RestaurantAdmin> restaurantAdminIdOptional = Optional.ofNullable(restaurantAdmin);
        UUID restaurantAdminId = null;
        if (restaurantAdminIdOptional.isPresent()){
            restaurantAdminId = restaurantAdminIdOptional.get().getId();
        }
        return new RestaurantAdminDto(restaurantAdminId);
    }
    // Add the foodMenuToDto method
    public static FoodMenuDto foodMenuToDto(FoodMenu foods, Restaurant restaurant) {

        RestaurantDto restaurantDto = restaurantToDto(restaurant);

        return new FoodMenuDto(foods.getId(),
                foods.getName(),
                foods.getDescription(),
                foods.getPrice(),
                foods.getImage(),
                restaurantDto);
    }
    // Add the foodMenuListToDtoList method
    public static List<RestaurantDto> restaurantListToDtoList(List<Restaurant> restaurantList) {
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for (var restaurant : restaurantList){
            // Converting to DTO
            RestaurantDto restaurantDto = restaurantToDto(restaurant);

            // Add the RestaurantDto to the list
            restaurantDtoList.add(restaurantDto);
        }
        return restaurantDtoList;
    }

    // Add the customerOrderToResponse method
    public static CustomerOrderDto customerOrderToResponse(CustomerOrder order){
        return new CustomerOrderDto(
                order.getOrderId(),
                order.getProductId(),
                order.getName(),
                order.getDescription(),
                order.getPrice(),
                order.getRestaurantId(),
                ModelMapper.userToDto(order.getUser())
        );
    }
    // Add the orderRequestToCustomerOrder method
    public static CustomerOrder orderRequestToCustomerOrder(OrderRequest order){
        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setProductId(order.id());
        customerOrder.setName(order.name());
        customerOrder.setDescription(order.description());
        customerOrder.setRestaurantId(order.restaurantId());
        customerOrder.setPrice(order.price());

        return customerOrder;
    }
}
