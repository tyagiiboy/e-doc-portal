package com.edoc.backend.controllers;

import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.User;
import com.edoc.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user){
    userService.registerStudent(user);
    return ResponseEntity.ok().build();

  }

  @PostMapping("/updateprofile")
  public ResponseEntity<?> updateUser(@RequestBody UserDto user){

  }
}

