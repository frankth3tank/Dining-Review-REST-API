package com.dining.diningreview.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dining.diningreview.model.User;
import com.dining.diningreview.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {

        validateUser(user);

        this.userRepository.save(user);
    }

    @PutMapping("/{displayName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInformation(@PathVariable String displayName, @RequestBody User updatedUser) {

        validateDisplayName(displayName);

        Optional<User> userToUpdateOptional = this.userRepository.findUserByDisplayName(displayName);
        if (userToUpdateOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User userUpdated = userToUpdateOptional.get();

        copyUserInfoToUpdate(updatedUser, userUpdated);
        userRepository.save(userUpdated);
    }

    @GetMapping("/{displayName}")
    public User getUser(@PathVariable String displayName) {

        validateDisplayName(displayName);

        Optional<User> existingUserOptional = this.userRepository.findUserByDisplayName(displayName);
        if (existingUserOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User existingUser = existingUserOptional.get();
        existingUser.setId(null);

        return existingUser;
    }

    private void copyUserInfoToUpdate(User updatedUser, User existingUser) {
        if (ObjectUtils.isEmpty(updatedUser.getDisplayName())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!ObjectUtils.isEmpty(updatedUser.getCity())) {
            existingUser.setCity(updatedUser.getCity());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getState())) {
            existingUser.setState(updatedUser.getState());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getZipcode())) {
            existingUser.setZipcode(updatedUser.getZipcode());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getIsAllergicToDairy())) {
            existingUser.setIsAllergicToDairy(updatedUser.getIsAllergicToDairy());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getIsAllergicToEggs())) {
            existingUser.setIsAllergicToEggs(updatedUser.getIsAllergicToEggs());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getIsAllergicToPeanuts())) {
            existingUser.setIsAllergicToPeanuts(updatedUser.getIsAllergicToPeanuts());
        }
    }

    private void validateUser(User user) {

        validateDisplayName(user.getDisplayName());

        Optional<User> existingUser = userRepository.findUserByDisplayName(user.getDisplayName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateDisplayName(String displayName) {

        if (ObjectUtils.isEmpty(displayName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
