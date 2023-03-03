package com.edoc.backend.services;

import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.AdmissionRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AdmissionRepository admissionRepository;

  public StudentDto getStudentById(long id) {
    User student = userRepository.getReferenceById(id);
    return StudentDto.builder()
        .id(student.getId())
        .email(student.getEmail())
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .schoolClass(admissionRepository
            .getLatestAdmissionDetailsById(id)
            .getSchoolClass())
        .build();
  }


}
