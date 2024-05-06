package org.fdschinecenter.service;

import org.fdschinecenter.dto.RestaurantDto;
import org.fdschinecenter.dto.UserDto;
import org.fdschinecenter.entity.User;

import java.util.List;

/**
 * Interface for User Service.
 */
public interface UserService {
    //create
    User saveUser(User user);

    // get
    UserDto getUser(String id);

    // verify user
    boolean isUserVerified(String verificationCode);


    UserDto updateUser(String id, User user);

    // delete
    void deleteUser(String id);

    List<User> allUsers();

    RestaurantDto getRestaurantByAdmin(String adminId);

}
