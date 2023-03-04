package com.edoc.backend.services;

import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ModelMapper mapper;

  public void registerStudent(User user) {
    userRepository.save(user);
  }

  public UserDto getProfileById(Long id) {
    User user = userRepository.findById(id).orElseThrow();
    return mapper.map(user, UserDto.class);
  }

  public void updateProfile(UserDto user) {
    User user1 = userRepository.findById(user.getId()).orElseThrow();
    user1.setRole(user.getRole());
    user1.setAddress(user.getAddress());
    user1.setAccoutNumber(user.getAccoutNumber());
    user1.setAddress(user.getAddress());
    user1.setFirstName(user.getFirstName());
    user1.setEmail(user.getEmail());
    user1.setLastName(user.getLastName());
    user1.setContactNo(user.getContactNo());
    userRepository.save(user1);
  }

}
