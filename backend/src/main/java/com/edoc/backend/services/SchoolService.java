package com.edoc.backend.services;

import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.School;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    public SchoolDto createSchool(School school) {
        School school1 = schoolRepository.save(school);
        return SchoolDto.builder()
                .diseCode(school1.getDiseCode())
                .coEd(school1.getCoEd())
                .level(school1.getLevel())
                .name(school.getName())
                .contactNo(school.getContact1())
                .build();
    }

    public void registerStudent(User user, Admission admission, long diseCode) {
        School school = schoolRepository.findById(diseCode).orElseThrow();
        school.getAdmissionList().add(admission);
        school.getUserList().add(user);
        admission.setSchool(school);
        admission.setUser(user);
        user.setSchool(school);
        user.getAdmissionList().add(admission);
        schoolRepository.save(school);
    }
}
