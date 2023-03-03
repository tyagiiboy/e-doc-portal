package com.edoc.backend.services;

import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.AdmissionRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdmissionRepository admissionRepository;

    public StudentDto getStudentById(long id){
        User student = userRepository.getReferenceById(id);
        return StudentDto.builder()
                .email(student.getEmail())
                .role(student.getRole())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .username(student.getUsername())
                .schoolClass(admissionRepository
                        .getLatestAdmissionDetailsById(id)
                        .getSchoolClass())
                .build();
    }


}
