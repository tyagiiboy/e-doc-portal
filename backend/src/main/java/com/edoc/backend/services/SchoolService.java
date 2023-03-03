package com.edoc.backend.services;

import com.edoc.backend.dto.ResponseMessage;
import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.School;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SchoolService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    public UserDto createAdmin(User user) {
        User save = userRepository.save(user);
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .disecode(user.getSchool().getDiseCode())
                .username(user.getUsername())
                .build();
    }

    public ResponseMessage countOfSchools() {
        return ResponseMessage.builder()
                .message("Stats")
                .obj(schoolRepository.count())
                .build();
    }

    public UserDto retrieveUserById(long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserDto.builder()
                .role(user.getRole())
                .disecode(user.getSchool().getDiseCode())
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
    }

}
