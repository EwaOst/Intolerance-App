package com.intolerance_app.service;

import com.intolerance_app.exception.UserAlreadyExistException;
import com.intolerance_app.model.IntoleranceModel;
import com.intolerance_app.model.UserModel;
import com.intolerance_app.repository.IntoleranceRepository;
import com.intolerance_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final IntoleranceRepository intoleranceRepository;

    private static final String USER_NOT_FOUND = "User not found";

    public UserService(UserRepository userRepository, IntoleranceRepository intoleranceRepository) {
        this.userRepository = userRepository;
        this.intoleranceRepository = intoleranceRepository;
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

    @Transactional
    public UserModel addIntoleranceToUser(Long userId, Long intoleranceId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        IntoleranceModel intolerance = intoleranceRepository.findById(intoleranceId)
                .orElseThrow(() -> new RuntimeException("Intolerance not found"));

        // Dodaj do użytkownika
        user.getIntolerances().add(intolerance);

        // Dodaj do nietolerancji powiązanego użytkownika (dwukierunkowa synchronizacja)
        if (intolerance.getUsers() == null) {
            intolerance.setUsers(new HashSet<>());
        }
        intolerance.getUsers().add(user);

        return userRepository.save(user);
    }

    @Transactional
    public UserModel removeIntoleranceFromUser(Long userId, Long intoleranceId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        IntoleranceModel intolerance = intoleranceRepository.findById(intoleranceId)
                .orElseThrow(() -> new RuntimeException("Intolerance not found"));

        // Usuń z użytkownika
        user.getIntolerances().remove(intolerance);

        // Usuń z nietolerancji powiązanego użytkownika (dwukierunkowa synchronizacja)
        if (intolerance.getUsers() != null) {
            intolerance.getUsers().remove(user);
        }

        return userRepository.save(user);
    }
}



