package com.edoc.backend.services;

import com.edoc.backend.dto.EventDto;
import com.edoc.backend.dto.SchoolDto;
import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.Event;
import com.edoc.backend.entities.School;
import com.edoc.backend.repositories.AdmissionRepository;
import com.edoc.backend.repositories.EventRepository;
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
public class EventService {
  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private AdmissionRepository admissionRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SchoolRepository schoolRepository;
  @Autowired
  private ModelMapper mapper;

  public EventDto createEvent(Event event, long diseCode) {
    School school = schoolRepository.findById(diseCode).orElseThrow();
    school.getEventList().add(event);
    event.setOrganizer(school);
    schoolRepository.save(school);

    EventDto dto = mapper.map(event, EventDto.class);
    System.out.println(dto.getOrganizer());
    dto.setOrganizer(mapper.map(school, SchoolDto.class));
    return dto;
  }

  public EventDto getEventById(Long id) {
    Event event = eventRepository.findById(id).orElseThrow();
    EventDto dto = mapper.map(event, EventDto.class);
    dto.setOrganizer(mapper.map(event.getOrganizer(), SchoolDto.class));
    return dto;
  }

  public List<EventDto> getEvents() {
    List<EventDto> events = new ArrayList<>();

    eventRepository.findAll()
        .forEach(
            event -> {
              EventDto dto = mapper.map(event, EventDto.class);
              dto.setOrganizer(mapper.map(event.getOrganizer(), SchoolDto.class));
              events.add(dto);
            }
        );

    return events;
  }

  public EventDto deleteEventById(long eventId) {
    Event event = eventRepository.findById(eventId).orElseThrow();

    event.getParticipants()
        .forEach(admission -> admission.getParticipations().remove(event));
    event.setParticipants(null);

    event.getOrganizer().getEventList().remove(event);
    event.setOrganizer(null);

    eventRepository.delete(event);
    return mapper.map(event, EventDto.class);
  }

  public EventDto updateEventById(EventDto eventDto) {
    Event event = eventRepository.findById(eventDto.getEventId()).orElseThrow();
    event.setDescription(eventDto.getDescription());
    event.setEventName(eventDto.getEventName());
    event.setEndDate(eventDto.getEndDate());
    event.setLastDateOfEnrollment(eventDto.getLastDateOfEnrollment());
    event.setStartDate(eventDto.getStartDate());
    eventRepository.save(event);
    return eventDto;
  }

  public List<UserDto> getParticipants(Long id) {
    Event event = eventRepository.findById(id).orElseThrow();
    List<UserDto> users = new ArrayList<>();
    event.getParticipants()
        .forEach((Admission admission) -> users.add(
            mapper.map(admission.getUser(), UserDto.class)
        ));
    return users;
  }

}
