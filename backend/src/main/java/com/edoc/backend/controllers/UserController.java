package com.edoc.backend.controllers;

import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.User;
import com.edoc.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user){
    userService.registerStudent(user);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody UserDto old){
    userService.updateProfile(old);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<?> userProfile(@PathVariable long userId) {
    return ResponseEntity.ok(userService.getProfileById(userId));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

}

