package com.intolerance_app.controller;

import com.intolerance_app.model.IntoleranceModel;
import com.intolerance_app.model.UserModel;
import com.intolerance_app.repository.IntoleranceRepository;
import com.intolerance_app.repository.UserRepository;
import com.intolerance_app.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final IntoleranceRepository intoleranceRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, IntoleranceRepository intoleranceRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.intoleranceRepository = intoleranceRepository;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel userModel) {
        UserModel newUser = userService.createUser(userModel);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id,
                                                @RequestBody UserModel user) {
        return userService.updateUser(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/{userId}/intolerances/{intoleranceId}")
    public ResponseEntity<UserModel> addIntoleranceToUser(
            @PathVariable Long userId,
            @PathVariable Long intoleranceId) {
        UserModel updatedUser = userService.addIntoleranceToUser(userId, intoleranceId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}/intolerances/{intoleranceId}")
    public ResponseEntity<UserModel> removeIntoleranceFromUser(
            @PathVariable Long userId,
            @PathVariable Long intoleranceId) {
        UserModel updatedUser = userService.removeIntoleranceFromUser(userId, intoleranceId);
        return ResponseEntity.ok(updatedUser);
    }

}


