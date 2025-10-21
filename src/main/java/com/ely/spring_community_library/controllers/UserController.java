package com.ely.spring_community_library.controllers;

import com.ely.spring_community_library.dtos.CreateUserDto;
import com.ely.spring_community_library.dtos.UpdateUserDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<User> createUser(@RequestBody @Valid CreateUserDto userDto) {

        final User createdUser = userService.createUser(userDto);
        final URI location = URI.create("/users/" + createdUser.getId());

        return ResponseEntity.created(location).body(createdUser);
    }

    @GetMapping
    private ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {

        final Optional<User> user = userService.getUserById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Optional<User>> updateUserById(@PathVariable("id") Long id,
                                                          @RequestBody @Valid UpdateUserDto userDto) {
        final Optional<User> updatedUser = userService.updateUserById(id, userDto);

        if(updatedUser.isPresent()) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
