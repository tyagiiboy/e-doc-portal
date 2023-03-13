package com.edoc.backend.controllers;

import com.edoc.backend.dto.AuthRequest;
import com.edoc.backend.dto.AuthResponse;
import com.edoc.backend.entities.School;
import com.edoc.backend.entities.User;
import com.edoc.backend.jwtutils.JwtUtils;
import com.edoc.backend.services.CustomUserDetails;
import com.edoc.backend.services.SchoolService;
import com.edoc.backend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@SuppressWarnings("unused")
public class SignInSignUpController {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private SchoolService schoolService;

  @Autowired
  private UserService userService;

  @PostMapping("/signin")
  public ResponseEntity<?> validateCredentials(@RequestBody AuthRequest req) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            req.getUsername(), req.getPassword()
        );

    System.out.println(req);

    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    AuthRequest authRequest = customUserDetails.getUser();
    AuthResponse authResponse = AuthResponse.builder()
        .token(jwtUtils.generateJwtToken(authentication))
        .id(authRequest.getId())
        .username(authRequest.getUsername())
        .role(authRequest.getRole())
        .build();
    return ResponseEntity.ok(authResponse);
  }

  @PostMapping("/signup/school")
  public ResponseEntity<?> registerSchool(@RequestBody School school) {
      return ResponseEntity.status(HttpStatus.CREATED).body(schoolService.createSchool(school));
  }

  @PostMapping("/signup/user")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    System.out.println("In regiser User" + user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
  }
}
