package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.CreateUserDto;
import com.ely.spring_community_library.dtos.UpdateUserDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

     public User createUser(CreateUserDto userDto) {

        final User newUser = new User(null, userDto.name(), userDto.email(), null, userDto.active());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {

         return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {

         return userRepository.findById(id);
    }

    public Optional<User> updateUserById(Long id, UpdateUserDto userDto) {

         Optional<User> oldUser = userRepository.findById(id);

         if(oldUser.isPresent()) {

             User updatedUser = oldUser.get();

             if(userDto.name() != null) {
                updatedUser.setName(userDto.name());
             }
             if(userDto.email() != null) {
                 updatedUser.setEmail(userDto.email());
             }
             updatedUser.setActive(userDto.active());

             return Optional.of(userRepository.save(updatedUser));
         } else {
             return Optional.empty();
         }
    }
}
