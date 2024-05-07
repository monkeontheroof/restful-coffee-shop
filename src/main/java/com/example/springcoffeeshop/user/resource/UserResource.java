package com.example.springcoffeeshop.user.resource;


import com.example.springcoffeeshop.user.entity.UserEntity;
import com.example.springcoffeeshop.user.service.UserService;
import com.example.springcoffeeshop.user.service.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name="User")
@RequestMapping("/v1/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(User user){
        User createdUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("users/" + createdUser.getId())).body(createdUser);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, User user){
        User updatedUser = userService.updateUserById(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
