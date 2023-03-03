package com.edoc.backend.services;

import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.entities.School;
import com.edoc.backend.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

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
}
