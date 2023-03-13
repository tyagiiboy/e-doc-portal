package com.edoc.backend.services;

import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("unused")
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private PasswordEncoder enc;

  @Modifying
  public void registerStudent(User user) {
    userRepository.save(user);
  }


  public UserDto createUser(User user) {
    user.setPassword(enc.encode(user.getPassword()));
    System.out.println("encoded: " + user.getPassword());
    return mapper.map(userRepository.save(user), UserDto.class);
  }

  public UserDto getProfileById(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    return mapper.map(user, UserDto.class);
  }

  @Modifying
  public void updateProfile(UserDto user) {
    User user1 = userRepository.findById(user.getId()).orElseThrow();
    user1.setRole(user.getRole());
    user1.setAddress(user.getAddress());
    user1.setAccountNumber(user.getAccountNumber());
    user1.setAddress(user.getAddress());
    user1.setFirstName(user.getFirstName());
    user1.setEmail(user.getEmail());
    user1.setLastName(user.getLastName());
    user1.setContactNo(user.getContactNo());
    userRepository.save(user1);
  }

  public List<UserDto> getUsers() {
    List<UserDto> users = new ArrayList<>();
    userRepository.findAll()
        .forEach(
            user -> users.add(mapper.map(user, UserDto.class))
        );
    return users;
  }

}
