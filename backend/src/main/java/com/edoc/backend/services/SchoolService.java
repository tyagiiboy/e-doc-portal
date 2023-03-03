package com.edoc.backend.services;

import com.edoc.backend.dto.ResponseMessage;
import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.entities.School;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SchoolService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SchoolRepository schoolRepository;

  public ResponseMessage countOfSchools() {
    return ResponseMessage.builder()
        .message("Stats")
        .obj(schoolRepository.count())
        .build();
  }

  public List<SchoolDto> getSchoolsByAuthorizationStatus(boolean status) {
    List<SchoolDto> schools = new ArrayList<>();
    schoolRepository
        .findByAuthorizationStatus(status)
        .forEach(
            school -> schools.add(
                SchoolDto.builder()
                    .authorizationStatus(school.getAuthorizationStatus())
                    .level(school.getLevel())
                    .coEd(school.getCoEd())
                    .name(school.getName())
                    .diseCode(school.getDiseCode())
                    .build()
            )
        );
    return schools;
  }

  public School unRegisterSchoolByDiseCode(Long diseCode) {
    School school = schoolRepository.findById(diseCode).orElseThrow();
    school.setAuthorizationStatus(false);
    schoolRepository.save(school);
    return school;
  }

}
