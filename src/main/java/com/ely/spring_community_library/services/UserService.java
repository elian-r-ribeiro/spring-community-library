package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.CreateUserDto;
import com.ely.spring_community_library.dtos.UpdateUserDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

     public ResponseEntity<User> createUser(CreateUserDto userDto) {

        final User newUser = new User(null, userDto.name(), userDto.email(), null, userDto.active());

        return ResponseEntity.ok(userRepository.save(newUser));
    }

    public List<User> getAllUsers() {

         return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id) {

         Optional<User> userEntity = userRepository.findById(id);

         if(userEntity.isPresent()) {

             return ResponseEntity.ok(userEntity.get());
         } else {

             return ResponseEntity.notFound().build();
         }
    }

    public ResponseEntity<User> updateUserById(Long id, UpdateUserDto userDto) {

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

             return ResponseEntity.ok(userRepository.save(updatedUser));
         } else {
             return ResponseEntity.notFound().build();
         }
    }

    public ResponseEntity<Void> deleteUserById(Long id) {

         Optional<User> userToDelete = userRepository.findById(id);

         if(userToDelete.isPresent()) {

             userRepository.deleteById(id);
             return ResponseEntity.noContent().build();
         } else{

             return ResponseEntity.notFound().build();
         }
    }
}
