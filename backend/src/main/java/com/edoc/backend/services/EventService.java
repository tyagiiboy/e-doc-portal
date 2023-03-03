package com.edoc.backend.services;

import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.Event;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event){
    Event event1 = eventRepository.save(event);
    return event;
    }

    public Event getEventById(Long id){
        Event event = eventRepository.findById(id).orElseThrow();

        return event;
    }

    public List<Event> getEventList(){
        return eventRepository.findAll();
    }

    public void deleteEventById(Long id){
        eventRepository.deleteById(id);
    }

    public List<User> getParticipants(Long id){
        Event event = eventRepository.findById(id).orElseThrow();
        List<User> users = new ArrayList<>();
        event.getParticipants()
                .forEach((Admission admission) -> users.add(admission.getUser()));
        return users;
    }

}
