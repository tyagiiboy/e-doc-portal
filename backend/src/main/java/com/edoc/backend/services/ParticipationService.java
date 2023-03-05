package com.edoc.backend.services;

import com.edoc.backend.dto.EventDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public Map<Long, List<EventDto>> getAllParticipationsOf(long userId) {
    List<Admission> admissionList = admissionRepository.findByUserId(userId);
    HashMap<Long, List<EventDto>> admissionsAndParticipations = new HashMap<>();

    admissionList.forEach(
        admission -> {
          List<EventDto> events = new ArrayList<>();
          admission
              .getParticipations()
              .forEach(
                  event -> events.add(mapper.map(event, EventDto.class))
              );
          admissionsAndParticipations
              .put(
                  admission.getAdmissionId(),
                  events
              );
        }
    );
    return admissionsAndParticipations;
  }

}
