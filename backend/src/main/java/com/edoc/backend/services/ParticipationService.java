package com.edoc.backend.services;

import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.Event;
import com.edoc.backend.entities.User;
import com.edoc.backend.enums.Role;
import com.edoc.backend.repositories.AdmissionRepository;
import com.edoc.backend.repositories.EventRepository;
import com.edoc.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ParticipationService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private AdmissionRepository admissionRepository;

  public List<UserDto> getParticipants(Long id) {
    Event event = eventRepository.findById(id).orElseThrow();
    List<UserDto> users = new ArrayList<>();
    event.getParticipants()
        .forEach((Admission admission) -> users.add(
            mapper.map(admission.getUser(), UserDto.class)
        ));
    return users;
  }

  public void addParticipant(long eventId, long id) {
    Event event = eventRepository.findById(eventId).orElseThrow();
    User student = userRepository.findByIdAndRole(id, Role.STUDENT).orElseThrow();

    Admission admission = (Admission) student
        .getAdmissionList()
        .stream().sorted()
        .toArray()[0];

    admission.getParticipations().add(event);
    event.getParticipants().add(admission);
    eventRepository.save(event);
  }

  public void removeParticipant(long eventId, long userId) {
    Event event = eventRepository.findById(eventId).orElseThrow();

    Admission admission = (Admission) admissionRepository
        .getAdmissionHistoryByUserId(userId)
        .stream()
        .sorted()
        .toArray()[0];

    admission.getParticipations().remove(event);
    event.getParticipants().remove(admission);

    eventRepository.save(event);
  }

}
