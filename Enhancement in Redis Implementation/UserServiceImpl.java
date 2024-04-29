package com.oviyahsridhar.fooddelivery.service.impl;

import com.oviyahsridhar.fooddelivery.dto.RestaurantDto;
import com.oviyahsridhar.fooddelivery.dto.UserDto;
import com.oviyahsridhar.fooddelivery.entity.Restaurant;
import com.oviyahsridhar.fooddelivery.entity.Role;
import com.oviyahsridhar.fooddelivery.entity.User;
import com.oviyahsridhar.fooddelivery.exceptions.PasswordMismatchException;
import com.oviyahsridhar.fooddelivery.exceptions.ResourceNotFoundException;
import com.oviyahsridhar.fooddelivery.exceptions.UserAlreadyExistsException;
import com.oviyahsridhar.fooddelivery.exceptions.UserNotFoundException;
import com.oviyahsridhar.fooddelivery.repository.RestaurantRepository;
import com.oviyahsridhar.fooddelivery.repository.UserRepository;
import com.oviyahsridhar.fooddelivery.service.UserService;
import com.oviyahsridhar.fooddelivery.utils.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;

    @Override
    public User saveUser(User user) {

        Optional<User> userByEmail = this.userRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistsException("User with this "+ user.getEmail() +" email already registered!");
        }

        user.setRole(Role.NORMAL);
        user.setUserRegistrationDate(LocalDateTime.now());
        user.setVerificationCode(null);

        if (!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordMismatchException("Password & Confirm Password Not Match!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        return this.userRepository.save(user);

    }

    private String generateVerificationCode(){
        return UUID.randomUUID().toString();
    }

    @Override
    public UserDto getUser(String id) {

        if (id.contains("@") && id.endsWith(".com")){
            return getUserByEmail(id);
        }

        User user = this.userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("User with this id: " + id + " not found!"));

        return ModelMapper.userToDto(user);
    }

    @Override
    public boolean isUserVerified(String verificationCode) {
        LocalDateTime currDate = LocalDateTime.now();
        Optional<User> optionalUser = this.userRepository.findUserByVerificationCode(verificationCode);
        if (optionalUser.isEmpty()){
            return false;
        }

        User user = optionalUser.get();
        LocalDateTime userRegistrationDate = user.getUserRegistrationDate();
        long minute = Duration.between(userRegistrationDate, currDate).toMinutes();
        System.err.println(minute);
        if (minute <= 5){
            user.setVerificationCode(null);
            user.setEnable(true);
            this.userRepository.save(user);
            return true;
        }
        this.userRepository.delete(user);
        return false;
    }

    private UserDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User with this email " + email + " not found"));
        return ModelMapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(String id, User user) {
        // I will implement this later
        return null;
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new UserNotFoundException("User with this id: " + id + " not found!"));
        this.userRepository.delete(user);
    }

    @Override
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

    // This api for admin panel
    @Override
//    @Cacheable(value = "restaurantByAdmin")
    public RestaurantDto getRestaurantByAdmin(String adminId) {
        Restaurant restaurant = this.restaurantRepository.findRestaurantByRestaurantAdminId(UUID.fromString(adminId))
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant Not FOUND! with this admin id: "+adminId));
        return ModelMapper.restaurantToDto(restaurant);
    }
}
