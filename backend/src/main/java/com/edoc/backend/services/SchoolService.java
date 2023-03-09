package com.edoc.backend.services;

import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.entities.School;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@SuppressWarnings("unused")
public class SchoolService {
  @Autowired
  private PasswordEncoder enc;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ModelMapper mapper;
  @Autowired
  private SchoolRepository schoolRepository;

  public Long countOfSchools() {
    return schoolRepository.count();
  }

  public List<SchoolDto> getSchoolListByAuthorizationStatus(boolean status) {
    List<SchoolDto> schools = new ArrayList<>();
    schoolRepository
        .findByAuthorizationStatus(status)
        .forEach(school -> schools.add(mapper.map(school, SchoolDto.class)));
    return schools;
  }

  public List<SchoolDto> getSchoolList() {
    List<SchoolDto> schools = new ArrayList<>();
    schoolRepository.findAll()
        .forEach(school -> schools.add(mapper.map(school, SchoolDto.class)));
    return schools;
  }

  public SchoolDto unAuthorizeSchoolByDiseCode(Long diseCode) {
    School school = schoolRepository.findById(diseCode).orElseThrow();
    school.setAuthorizationStatus(false);
    schoolRepository.save(school);
    return mapper.map(school, SchoolDto.class);
  }

  @Modifying
  public SchoolDto createSchool(School school) {
    school.setPassword(enc.encode(school.getPassword()));
    schoolRepository.findById(school.getDiseCode());
    school.setAuthorizationStatus(false);
    return mapper.map(schoolRepository.save(school), SchoolDto.class);
  }

  public SchoolDto getSchoolByDiseCode(long diseCode) {
    return mapper.map(schoolRepository.findById(diseCode), SchoolDto.class);
  }

  public List<SchoolDto> setAuthorizationOfSchoolsInList(List<SchoolDto> schools, boolean status) {
    Set<SchoolDto> set = new HashSet<>(schools);
    List<School> all = schoolRepository.findAll();

    all = all.stream()
        .filter(school -> set.contains(mapper.map(school, SchoolDto.class)))
        .collect(Collectors.toList());
    all.forEach(school -> school.setAuthorizationStatus(status));

    schoolRepository.saveAll(all);

    return schools;
  }

  @Modifying
  public boolean removeStudentsInList(List<StudentDto> studentDtos) {
    if (studentDtos.size() == 0) return false;
    long diseCode = studentDtos.get(0).getSchool().getDiseCode();
    School school = schoolRepository
        .findById(diseCode)
        .orElseThrow();
    school
        .getUserList()
        .stream()
        .filter(
            user -> studentDtos.contains(mapper.map(user, StudentDto.class))
        )
        .forEach(
            user -> {
              user.setSchool(null);
              school.getUserList().remove(user);
            }
        );
    schoolRepository.save(school);
    return true;
  }


}












