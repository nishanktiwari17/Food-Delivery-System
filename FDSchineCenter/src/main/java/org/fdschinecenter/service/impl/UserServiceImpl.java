package org.fdschinecenter.service.impl;

import org.fdschinecenter.dto.RestaurantDto;
import org.fdschinecenter.dto.UserDto;
import org.fdschinecenter.entity.Restaurant;
import org.fdschinecenter.entity.Role;
import org.fdschinecenter.entity.User;
import org.fdschinecenter.exceptions.PasswordMismatchException;
import org.fdschinecenter.exceptions.ResourceNotFoundException;
import org.fdschinecenter.exceptions.UserAlreadyExistsException;
import org.fdschinecenter.exceptions.UserNotFoundException;
import org.fdschinecenter.repository.RestaurantRepository;
import org.fdschinecenter.repository.UserRepository;
import org.fdschinecenter.service.UserService;
import org.fdschinecenter.utils.ModelMapper;
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
/**
 * Class to manage User Service.
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;

    /**
     * Method to save a user.
     * @param user
     * @return User
     */
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

    /**
     * Method to generate a verification code.
     * @return String
     */
    private String generateVerificationCode(){
        return UUID.randomUUID().toString();
    }

    @Override
    /**
     * Method to get a user.
     * @param id
     * @return UserDto
     */
    public UserDto getUser(String id) {

        if (id.contains("@") && id.endsWith(".com")){
            return getUserByEmail(id);
        }

        User user = this.userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException("User with this id: " + id + " not found!"));

        return ModelMapper.userToDto(user);
    }

    /**
     * Method to verify a user.
     * @param verificationCode
     * @return boolean
     */
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

    /**
     * Method to get a user by email.
     * @param email
     * @return UserDto
     */
    private UserDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User with this email " + email + " not found"));
        return ModelMapper.userToDto(user);
    }

    /**
     * Method to update a user.
     * @param id
     * @param user
     * @return UserDto
     */
    @Override
    public UserDto updateUser(String id, User user) {
        // I will implement this later
        return null;
    }

    /**
     * Method to delete a user.
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new UserNotFoundException("User with this id: " + id + " not found!"));
        this.userRepository.delete(user);
    }

    /**
     * Method to get all users.
     * @return List<User>
     */
    @Override
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Method to get a restaurant by admin.
     * @param adminId
     * @return RestaurantDto
     */
    @Override
//    @Cacheable(value = "restaurantByAdmin")
    public RestaurantDto getRestaurantByAdmin(String adminId) {
        Restaurant restaurant = this.restaurantRepository.findRestaurantByRestaurantAdminId(UUID.fromString(adminId))
                .orElseThrow(()-> new ResourceNotFoundException("Restaurant Not FOUND! with this admin id: "+adminId));
        return ModelMapper.restaurantToDto(restaurant);
    }
}