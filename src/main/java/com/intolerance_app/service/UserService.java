package com.intolerance_app.service;

import com.intolerance_app.exception.UserAlreadyExistException;
import com.intolerance_app.model.UserModel;
import com.intolerance_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND = "User not found";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserModel> updateUser(Long id, UserModel updateUser) {
        return Optional.ofNullable(userRepository.findById(id)
                .map(user -> {
                    user.setUserSurname(updateUser.getUserSurname());
                    user.setUserSurname(updateUser.getUserSurname());
                    user.setEmail(updateUser.getEmail());
                    user.setPassword(updateUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new EntityNotFoundException("User do not exist")));
    }

    public UserModel createUser(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new UserAlreadyExistException("User already exist with e-mail: " + userModel.getEmail());
        }
        return userRepository.save(userModel);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();

    }

    public Optional<UserModel> getUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}



