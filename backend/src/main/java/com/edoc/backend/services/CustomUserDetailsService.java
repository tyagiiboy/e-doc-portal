package com.edoc.backend.services;

import com.edoc.backend.dto.AuthRequest;
import com.edoc.backend.entities.School;
import com.edoc.backend.entities.User;
import com.edoc.backend.enums.Role;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SchoolRepository schoolRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {

      User user = userRepository
          .findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("Credentials error"));

      return new CustomUserDetails(
          AuthRequest.builder()
              .role(user.getRole())
              .password(user.getPassword())
              .username(user.getUsername())
              .build()
      );

    } catch (UsernameNotFoundException ex) {

    }

    School school = schoolRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Credentials error"));

    return new CustomUserDetails(
        AuthRequest.builder()
            .role(Role.SCHOOL)
            .password(school.getPassword())
            .username(school.getUsername())
            .build()
    );

  }

}
