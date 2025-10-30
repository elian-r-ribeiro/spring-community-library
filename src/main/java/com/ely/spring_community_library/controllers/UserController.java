package com.ely.spring_community_library.controllers;

import com.ely.spring_community_library.dtos.UserDtos.UpdateUserDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUserById(@PathVariable("id") Long id) {

        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<User> updateUserById(@PathVariable("id") Long id,
                                                @RequestBody @Valid UpdateUserDto userDto) {
        return userService.updateUserById(id, userDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {

        return userService.deleteUserById(id);
    }
}
