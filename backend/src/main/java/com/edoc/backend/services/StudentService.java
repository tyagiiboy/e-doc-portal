package com.edoc.backend.services;

import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.School;
import com.edoc.backend.entities.User;
import com.edoc.backend.enums.Role;
import com.edoc.backend.repositories.AdmissionRepository;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ModelMapper mapper;
  @Autowired
  private AdmissionRepository admissionRepository;

  @Autowired
  private SchoolRepository schoolRepository;
  public StudentDto getStudentById(long id) {
    User student = userRepository.getReferenceById(id);
    StudentDto studentDto = mapper.map(student, StudentDto.class);
    studentDto.setSchool(mapper.map(student.getSchool(), SchoolDto.class));
    return studentDto;
  }

  public List<StudentDto> getStudents() {
    List<StudentDto> students = new ArrayList<>();
    userRepository.findByRole(Role.STUDENT).forEach(user -> students.add(
        StudentDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .school(SchoolDto.builder()
                .level(user.getSchool().getLevel())
                .authorizationStatus(user.getSchool().getAuthorizationStatus())
                .coEd(user.getSchool().getCoEd())
                .diseCode(user.getSchool().getDiseCode())
                .name(user.getSchool().getName())
                .build())
            .build()
    ));
    return students;
  }

  public boolean existsById(long id) {
    userRepository.findByIdAndRole(id, Role.STUDENT).orElseThrow();
    return true;
  }



}
