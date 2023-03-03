package com.edoc.backend.services;

import com.edoc.backend.dto.EventDto;
import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.dto.StudentDto;
import com.edoc.backend.entities.Admin;
import com.edoc.backend.entities.School;
import com.edoc.backend.enums.Role;
import com.edoc.backend.repositories.AdminRepository;
import com.edoc.backend.repositories.EventRepository;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {
  @Autowired
  private AdminRepository adminRepository;
  @Autowired
  private SchoolRepository schoolRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EventRepository eventRepository;

  public Admin createAdmin(Admin admin) {
    return adminRepository.save(admin);
  }

  public Admin updateAdmin(Admin admin) {
    return adminRepository.save(admin);
  }

  public List<SchoolDto> schools() {
    List<SchoolDto> schools = new ArrayList<>();
    schoolRepository.findAll().forEach(school -> schools
        .add(SchoolDto.builder()
            .name(school.getName())
            .level(school.getLevel())
            .diseCode(school.getDiseCode())
            .coEd(school.getCoEd())
            .authorizationStatus(school.getAuthorizationStatus())
            .build()));
    return schools;
  }

  public SchoolDto getSchoolById(long diseCode) {
    School school = schoolRepository.findById(diseCode).orElseThrow();
    return SchoolDto.builder()
        .authorizationStatus(school.getAuthorizationStatus())
        .name(school.getName())
        .level(school.getLevel())
        .diseCode(school.getDiseCode())
        .coEd(school.getCoEd())
        .authorizationStatus(school.getAuthorizationStatus())
        .build();
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

  public List<EventDto> getEvents() {
    List<EventDto> events = new ArrayList<>();
    eventRepository.findAll()
        .forEach(
            event -> events.add(
                EventDto.builder()
                    .eventId(event.getEventId())
                    .eventName(event.getEventName())
                    .endDate(event.getEndDate())
                    .description(event.getDescription())
                    .lastDateOfEnrollment(event.getLastDateOfEnrollment())
                    .announcementDate(event.getAnnouncementDate())
                    .startDate(event.getStartDate())
                    .school(SchoolDto.builder()
                        .name(event.getOrganizer().getName())
                        .diseCode(event.getOrganizer().getDiseCode())
                        .coEd(event.getOrganizer().getCoEd())
                        .authorizationStatus(event.getOrganizer().getAuthorizationStatus())
                        .level(event.getOrganizer().getLevel())
                        .build())
                    .build()
            )
        );
    return events;
  }

  public School unRegisterSchoolByDiseCode(Long diseCode) {
    School school = schoolRepository.findById(diseCode).orElseThrow();
    school.setAuthorizationStatus(false);
    schoolRepository.save(school);
    return school;
  }

}
